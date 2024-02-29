//
//  ColorExtension.swift
//  iosApp
//
//  Created by Szymon M on 29/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension Color {
    init(colorResource: KeyPath<SharedRes.colors, ResourcesColorResource>) {
        self.init(uiColor: SharedRes.colors()[keyPath: colorResource].getUiColor())
    }
}
