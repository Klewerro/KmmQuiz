package com.klewerro.kmmquiz.data.local.keyvalue

import com.klewerro.kmmquiz.domain.KeyValueStorage
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getIntFlow
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSettingsApi::class)
class SharedKeyValueStorage : KeyValueStorage {

    private val settings: Settings by lazy { Settings() } // blocking settings
    private val observableSettings: ObservableSettings by lazy { settings as ObservableSettings } // flow settings

    override var amount: Int?
        get() = settings[StorageKeys.AMOUNT.name]
        set(value) {
            settings[StorageKeys.AMOUNT.name] = value
        }

    override var questionCategory: Int?
        get() = settings[StorageKeys.QUESTION_CATEGORY.name]
        set(value) {
            settings[StorageKeys.QUESTION_CATEGORY.name] = value
        }

    override val amountFlow: Flow<Int> = observableSettings
        .getIntFlow(StorageKeys.AMOUNT.name, -1)
    override val questionCategoryIdFlow: Flow<Int> = observableSettings
        .getIntFlow(StorageKeys.QUESTION_CATEGORY.name, -1)

    override fun cleanStorage() {
        settings.clear()
    }
}
