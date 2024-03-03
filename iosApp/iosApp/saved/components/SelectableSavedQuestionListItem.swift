//
//  SelectableSavedQuestionListItem.swift
//  iosApp
//
//  Created by Szymon M on 03/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SelectableSavedQuestionListItem: View {
    var selectableSavedQuestion: SelectableSavedQuestion
    var onDeleteClick: () -> Void
    var onSelectedChanged: (Bool) -> Void
    var backgroundColorSelected: Color = Color(SharedRes.colors().secondaryContainer.getUiColor())
    var backgroundColorUnselected: Color = Color(SharedRes.colors().tertiaryContainer.getUiColor())
    var onBackgroundColorSelected: Color = Color(SharedRes.colors().onSecondaryContainer.getUiColor())
    var onBackgroundColorUnselected: Color = Color(SharedRes.colors().onTertiaryContainer.getUiColor())
    
    @State private var selected: String = ""
    var body: some View {
        Button(action: {
            onSelectedChanged(!selectableSavedQuestion.isSelected)
        }) {
            VStack {
                HStack {
                    Text(selectableSavedQuestion.question.text)
                        .foregroundStyle(selectableSavedQuestion.isSelected ?
                                         onBackgroundColorSelected : onBackgroundColorUnselected
                        )
                        .lineLimit(10)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(8)
                    Button(action: onDeleteClick) {
                        Image(systemName: "trash")
                            .font(.system(size: 25))
                            .foregroundStyle(Color(SharedRes.colors().onErrorContainer.getUiColor()))
                            .frame(width: 40, height: 40)
                    }
                    .background(
                        Circle()
                            .fill(Color(SharedRes.colors().errorContainer.getUiColor()))
                    )
                    
                }
                List {
                    Picker("Answers", selection: $selected) {
                        ForEach(selectableSavedQuestion.question.allAnswers, id: \.self) { answerText in
                            Text(answerText)
                                .foregroundStyle(selectableSavedQuestion.isSelected ?
                                                 onBackgroundColorSelected : onBackgroundColorUnselected
                                )
                        }
                    }
                    .pickerStyle(.inline)
                    .listRowBackground(selectableSavedQuestion.isSelected ? backgroundColorSelected : backgroundColorUnselected)
                }
                .scrollContentBackground(.hidden)
                .background(selectableSavedQuestion.isSelected ? backgroundColorSelected : backgroundColorUnselected)
            }
        }
        .frame(maxWidth: .infinity, minHeight: CGFloat(selectableSavedQuestion.question.type == .multiple ? 340 : 220), alignment: .leading)
        .padding(8)
        .background(selectableSavedQuestion.isSelected ? backgroundColorSelected : backgroundColorUnselected)
        .cornerRadius(15.0)
    }
}

#Preview {
    SelectableSavedQuestionListItem(
        selectableSavedQuestion: SelectableSavedQuestion(
            question: Question(
                category: "General knowledge",
                difficulty: .medium,
                correctAnswer: "Correct answer",
                incorrectAnswers: [
                    "Incorrect 1",
                    "Incorrect 2",
                    "Incorrect 3",
                ],
                type: .multiple,
                text: "Question text description.",
                allAnswers: [
                    "Incorrect 1",
                    "Incorrect 2",
                    "Correct answer",
                    "Incorrect 3",
                ]
            ),
            isSelected: true
        ),
        onDeleteClick: {},
        onSelectedChanged: {_ in}
    )
}

#Preview {
    SelectableSavedQuestionListItem(
        selectableSavedQuestion: SelectableSavedQuestion(
            question: Question(
                category: "General knowledge",
                difficulty: .medium,
                correctAnswer: "Correct answer",
                incorrectAnswers: [
                    "Incorrect 1",
                    "Incorrect 2",
                    "Incorrect 3",
                ],
                type: .multiple,
                text: "Question text description.",
                allAnswers: [
                    "Incorrect 1",
                    "Incorrect 2",
                    "Correct answer",
                    "Incorrect 3",
                ]
            ),
            isSelected: false
        ),
        onDeleteClick: {},
        onSelectedChanged: {_ in}
    )
}
