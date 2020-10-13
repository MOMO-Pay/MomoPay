package com.hover.stax.views;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.hover.stax.R;

public class StaxDialog extends AlertDialog {

	private Context context;
	private View view;
	public AlertDialog dialog;

	View.OnClickListener customNegListener;
	View.OnClickListener customPosListener;

	public StaxDialog(@NonNull Activity c) {
		super(c);
		context = c;
		LayoutInflater inflater = c.getLayoutInflater();
		view = inflater.inflate(R.layout.stax_dialog, null);
	}

	public StaxDialog setDialogTitle(int title) {
		((TextView) view.findViewById(R.id.title)).setText(context.getString(title));
		return this;
	}

	public StaxDialog setDialogMessage(int message) {
		((TextView) view.findViewById(R.id.message)).setText(context.getString(message));
		return this;
	}

	public StaxDialog setPosButton(int label, View.OnClickListener listener) {
		((AppCompatButton) view.findViewById(R.id.pos_btn)).setText(context.getString(label));
		customPosListener = listener;
		((AppCompatButton) view.findViewById(R.id.pos_btn)).setOnClickListener(posListener);
		return this;
	}

	public StaxDialog setNegButton(int label, View.OnClickListener listener) {
		view.findViewById(R.id.neg_btn).setVisibility(View.VISIBLE);
		((AppCompatButton) view.findViewById(R.id.neg_btn)).setText(context.getString(label));
		customNegListener = listener;
		((AppCompatButton) view.findViewById(R.id.neg_btn)).setOnClickListener(negListener);
		return this;
	}

	public StaxDialog isDestructive() {
		((AppCompatButton) view.findViewById(R.id.pos_btn)).getBackground()
			.setColorFilter(context.getResources().getColor(R.color.bright_red), PorterDuff.Mode.SRC);
		return this;
	}

	public void show() {
		dialog = new AlertDialog.Builder(context, R.style.StaxDialog).setView(view).create();
		dialog.show();
	}

	private View.OnClickListener negListener = view -> {
		customNegListener.onClick(view);
		dialog.dismiss();
	};

	private View.OnClickListener posListener = view -> {
		customPosListener.onClick(view);
		dialog.dismiss();
	};
}
