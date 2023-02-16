/*
 * Copyright 2022 Stax
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hover.stax.data.local.accounts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hover.stax.domain.model.Account
import com.hover.stax.domain.model.USDCAccount
import com.hover.stax.domain.model.USSDAccount
import com.hover.stax.storage.user.dao.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface USSDAccountDao : BaseDao<Account> {

    @Query("SELECT * FROM ussd_accounts WHERE institution_type != 'telecom' ORDER BY alias ASC")
    fun getAllAccounts(): List<USSDAccount>

    @Query("SELECT * FROM accounts ORDER BY alias ASC")
    fun getLiveAccounts(): LiveData<List<Account>>

    @Query("SELECT * FROM usdc_accounts ORDER BY alias ASC")
    fun getUSDCAccounts(): LiveData<List<USDCAccount>>

    @Query("SELECT * FROM ussd_accounts WHERE sim_subscription_id IN (:sim_subscriptionIds)")
    fun getAccountsBySubscribedSim(sim_subscriptionIds: IntArray): Flow<List<USSDAccount>>

    @Query("SELECT * FROM ussd_accounts WHERE institution_type = 'telecom' AND sim_subscription_id = (:subscriptionId)")
    fun getAccountBySim(subscriptionId: Int): USSDAccount?

    @Query("SELECT * FROM ussd_accounts WHERE channelId = :channelId AND institution_type != 'telecom' ORDER BY alias ASC")
    fun getAccountsByChannel(channelId: Int): List<USSDAccount>

    @Query("SELECT * FROM ussd_accounts WHERE institutionId = :institutionId AND institution_type != 'telecom' ORDER BY alias ASC")
    fun getAccountsByInstitution(institutionId: Int): LiveData<List<USSDAccount>>

    @Query("SELECT * FROM ussd_accounts WHERE institution_type != 'telecom' ORDER BY alias ASC")
    fun getAccounts(): Flow<List<USSDAccount>>

    @Query("SELECT * FROM ussd_accounts where name = :name and channelId = :channelId AND institution_type != 'telecom'")
    fun getAccount(name: String, channelId: Int): USSDAccount?

    @Query("SELECT * FROM ussd_accounts where id = :id")
    fun getAccount(id: Int): USSDAccount?

    @Query("SELECT * FROM ussd_accounts where id = :id")
    fun getLiveAccount(id: Int?): LiveData<USSDAccount>

    @Query("SELECT * FROM ussd_accounts  WHERE isDefault = 1 AND institution_type != 'telecom'")
    fun getDefaultAccount(): USSDAccount?

    @Query("SELECT * FROM ussd_accounts WHERE isDefault = 1 AND institution_type != 'telecom'")
    suspend fun getDefaultAccountAsync(): USSDAccount?

    @Query("SELECT COUNT(id) FROM accounts")
    fun getDataCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun ins(account: Account): Long

    @Insert
    suspend fun insertAll(accounts: List<Account>): List<Long>

    @Query("DELETE FROM accounts")
    fun deleteAll()

    @Query("DELETE FROM ussd_accounts WHERE channelId = :channelId AND name = :name")
    fun delete(channelId: Int, name: String)
}