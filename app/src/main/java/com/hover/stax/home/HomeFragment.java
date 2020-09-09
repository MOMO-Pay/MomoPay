package com.hover.stax.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.hover.sdk.api.Hover;
import com.hover.sdk.transactions.Transaction;
import com.hover.stax.ApplicationInstance;
import com.hover.stax.R;
import com.hover.stax.channels.Channel;
import com.hover.stax.security.PermissionScreenActivity;
import com.hover.stax.utils.TimeAgo;
import com.hover.stax.utils.UIHelper;
import com.hover.stax.utils.interfaces.CustomOnClickListener;

import java.util.List;

public class HomeFragment extends Fragment{

	private HomeViewModel homeViewModel;
	private RecyclerView recyclerView, transactionHistoryRecyclerView;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
		transactionHistoryRecyclerView = view.findViewById(R.id.transaction_history_recyclerView);
		final TextView textView = view.findViewById(R.id.text_balances);
		textView.setOnClickListener(v -> startActivity(new Intent(getActivity(), PermissionScreenActivity.class)));

		recyclerView = view.findViewById(R.id.balances_recyclerView);
		recyclerView.setLayoutManager(UIHelper.setMainLinearManagers(getContext()));
		recyclerView.setHasFixedSize(true);

		homeViewModel.getSelectedChannels().observe(getViewLifecycleOwner(), channels -> {
			recyclerView.setAdapter(new BalanceAdapter(channels, (MainActivity) getActivity()));
			setMeta(view, channels);
		});

		homeViewModel.getTransactions().observe(getViewLifecycleOwner(), transactions -> {
			homeViewModel.getTransactionModels(transactions).observe(getViewLifecycleOwner(), staxTransactionModels -> {
				transactionHistoryRecyclerView.setLayoutManager(UIHelper.setMainLinearManagers(getContext()));
				transactionHistoryRecyclerView.setAdapter(new TransactionHistoryAdapter(staxTransactionModels));
			});
		});




	}

	private void setMeta(View view, List<Channel> channels) {
		TextView homeTimeAgo = view.findViewById(R.id.homeTimeAgo);
		long mostRecentTimestamp = 0;
		for (Channel c : channels) {
			if (c.latestBalanceTimestamp != null && c.latestBalanceTimestamp > mostRecentTimestamp)
				mostRecentTimestamp = c.latestBalanceTimestamp;
		}
		homeTimeAgo.setText(mostRecentTimestamp > 0 ? TimeAgo.timeAgo(ApplicationInstance.getContext(), mostRecentTimestamp) : "Refresh");
		homeTimeAgo.setOnClickListener(view2 -> ((MainActivity) getActivity()).runAllBalances());

		view.findViewById(R.id.homeTimeAgo).setVisibility(channels.size() > 0 ? View.VISIBLE : View.GONE);
		view.findViewById(R.id.homeBalanceDesc).setVisibility(channels.size() > 0 ? View.GONE : View.VISIBLE);

		transactionHistoryRecyclerView.setVisibility(channels.size() > 0 ? View.VISIBLE : View.GONE);
		view.findViewById(R.id.transactionsLabel).setVisibility(channels.size() > 0 ? View.VISIBLE : View.GONE);
	}

}
