plugins {
    alias(libs.plugins.mifospay.android.feature)
    alias(libs.plugins.mifospay.android.library.compose)
}

android {
    namespace = "org.mifospay.invoices"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.feature.receipt)
}