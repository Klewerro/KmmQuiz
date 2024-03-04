//
//  IOSQuizDetailsViewModel.swift
//  iosApp
//
//  Created by Szymon M on 04/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class IOSQuizDetailsViewModel: ObservableObject {
    var quizId: Int64
    var localDbDataSource: LocalDbDataSource
    private let viewModel: QuizDetailsViewModel
    
    private var handle: DisposableHandle?
    @Published var state = QuizDetailsState(
        quiz: nil,
        answers: [:],
        getQuizError: nil
    )
    
    init(quizId: Int64, localDbDataSource: LocalDbDataSource) {
        self.quizId = quizId
        self.localDbDataSource = localDbDataSource
        self.viewModel = QuizDetailsViewModel(
            quizId: quizId,
            localDbDataSource: localDbDataSource,
            coroutineScope: nil
        )
    }
    
    func onEvent(_ event: QuizDetailsEvent) {
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
