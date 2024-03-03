//
//  ProgressButton.swift
//  iosApp
//
//  Created by Szymon M on 01/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProgressButton: View {
    var isLoading: Bool
    var labelText: String
    var progressText: String
    var onClick: () -> Void
    
    
    
    var body: some View {
        Button(action: onClick) {
            if (isLoading) {
                ProgressView(progressText)
                    .foregroundStyle(Color(SharedRes.colors().primary.getUiColor()))
                    .tint(Color(SharedRes.colors().primary.getUiColor()))
            } else {
                Text(labelText)
                    .foregroundStyle(Color(SharedRes.colors().primary.getUiColor()))
            }
        }
    }
}

#Preview {
    ProgressButton(
        isLoading: false,
        labelText: "Label text",
        progressText: "Progress text",
        onClick: {}
    )
}

#Preview {
    ProgressButton(
        isLoading: true,
        labelText: "Label text",
        progressText: "Progress text",
        onClick: {}
    )
}
