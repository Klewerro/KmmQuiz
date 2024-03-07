//
//  QuizDetailsScreen.swift
//  iosApp
//
//  Created by Szymon M on 04/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct QuizDetailsScreen: View {
    var quiz: Quiz
//    @ObservedObject var viewModel: IOSQuizDetailsViewModel
    
    init(quiz: Quiz) {
        self.quiz = quiz
//        self.viewModel = viewModel
    }
    
    
    var body: some View {
        VStack {
            Text("Quiz details screen. Quiz title is: " + quiz.title)
        }
    }
}
