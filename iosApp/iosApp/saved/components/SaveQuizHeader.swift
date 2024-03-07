//
//  SaveQuizHeader.swift
//  iosApp
//
//  Created by Szymon M on 04/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SaveQuizHeader: View {
    @Binding var quizTitleText: String
    var onSaveButtonClick: () -> Void
    
    var body: some View {
        HStack {
            TextField(
                SharedStrings().get(id: SharedRes.strings().quiz_name, args: []),
                text: $quizTitleText
            )
            .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
            .accentColor(Color(SharedRes.colors().onPrimary.getUiColor()))
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .padding()
            .background(Color(SharedRes.colors().primary.getUiColor()))
            .padding(.bottom, 4)
            .cornerRadius(4)
            .padding(.bottom, -4)
            .shadow(radius: 4)
            .padding(8)
            Button(action: onSaveButtonClick) {
                Image(systemName: "checkmark.rectangle.portrait")
                    .font(.system(size: 25))
                    .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
                    .frame(width: 40, height: 40)
            }
            .background(
                Circle()
                    .fill(Color(SharedRes.colors().primary.getUiColor()))
            )
        }
        .frame(width: .infinity, height: 80)
    }
}

#Preview {
    SaveQuizHeader(
        quizTitleText: Binding(
            get: { "Text" },
            set: {_ in}
        ),
        onSaveButtonClick: {}
    )
}
