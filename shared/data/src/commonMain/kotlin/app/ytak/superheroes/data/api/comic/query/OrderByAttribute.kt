package app.ytak.superheroes.data.api.comic.query

import app.ytak.superheroes.data.api.OrderBy

enum class OrderByAttribute(override val keyName: String) : OrderBy.Attribute {
    FocDate("focDate"),
    OnsaleDate("onsaleDate"),
    Title("title"),
    IssueNumber("issueNumber"),
    Modified("modified")
}
