package service

enum class RetrieveMode {
    CLOSEST_IN_AISLE,
    CLOSEST_AVAILABLE,

    HIGHEST_PRIORITY_IN_AISLE,
    HIGHEST_PRIORITY_AVAILABLE,
    HIGHEST_PRIORITY_ON_WAY_OUT
}