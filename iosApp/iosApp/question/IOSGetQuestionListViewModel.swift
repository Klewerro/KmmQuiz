//
//  IOSGetQuestionListViewModel.swift
//  iosApp
//
//  Created by Szymon M on 01/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class IOSGetQuestionListViewModel: ObservableObject {
    private var getQuestionListUseCase: GetQuestionListUseCase
    private var keyValueStorage: KeyValueStorage
    private var localDbDataSource: LocalDbDataSource
    private var handle: DisposableHandle?
    
    private let viewModel: GetQuestionListViewModel
    @Published var state = GetQuestionListState(
        isFetchingData: false,
        questions: [],
        error: nil,
        amountOfQuestions: GetQuestionListState.Companion().DEFAULT_QUESTION_AMOUNT,
        questionCategory: .generalKnowledge,
        isChoosingQuestionCategory: false
    )
    
    init(getQuestionListUseCase: GetQuestionListUseCase, keyValueStorage: KeyValueStorage, localDbDataSource: LocalDbDataSource) {
        self.getQuestionListUseCase = getQuestionListUseCase
        self.keyValueStorage = keyValueStorage
        self.localDbDataSource = localDbDataSource
        self.viewModel = GetQuestionListViewModel(
            getQuestionListUseCase: getQuestionListUseCase,
            keyValueStorage: keyValueStorage,
            localDbDataSource: localDbDataSource,
            coroutineScope: nil
        )
    }
    
    func onEvent(_ event: GetQuestionListEvent) {
        self.viewModel.onEvent(event: event)
    }
    
    func startObserving() {
        handle = viewModel.state.subscribe(onCollect: { state in
            if let state = state {
                self.state = state
            }
        })
    }
    
    func dispose() {
        handle?.dispose()
    }
}
