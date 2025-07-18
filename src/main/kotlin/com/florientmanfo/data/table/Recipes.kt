package com.florientmanfo.com.florientmanfo.data.table

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object Recipes : IdTable<String>("recipes") {
    override val id = varchar("id", 255).entityId()
    val name = varchar("name", 255)
    val description = text("description")
    val imageUrl = varchar("image_path", 512).nullable()
    val instructions = text("instructions")
    val authorId = varchar("author_id", 255).references(Users.id, onDelete = ReferenceOption.CASCADE)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
    override val primaryKey = PrimaryKey(id)
}
