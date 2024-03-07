//
//  IOSQuizViewModel.swift
//  iosApp
//
//  Created by Szymon M on 04/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class IOSQuizViewModel: ObservableObject {
    private var quizListUseCase: QuizListUseCase
    private var handle: DisposableHandle?
    private let viewModel: QuizViewModel
    
    @Published var state = QuizState(quizList: [])
    
    init(quizListUseCase: QuizListUseCase) {
        self.quizListUseCase = quizListUseCase
        self.viewModel = QuizViewModel(
            quizListUseCase: quizListUseCase,
            coroutineScope: nil
        )
    }
    
    func onEvent(_ event: QuizEvent) {
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
