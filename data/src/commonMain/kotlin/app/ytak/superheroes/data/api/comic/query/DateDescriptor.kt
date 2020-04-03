package app.ytak.superheroes.data.api.comic.query

enum class DateDescriptor(val code: String) {
    LastWeek("lastWeek"),
    ThisWeek("thisWeek"),
    NextWeek("nextWeek"),
    ThisMonth("thisMonth"),
}
