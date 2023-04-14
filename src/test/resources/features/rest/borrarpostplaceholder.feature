Feature: Borrar un post de PlaceHolder
  Yo como usuario de la API de PlaceHolder
  Quiero poder eliminar un registro de un post
  Para poder eliminar un post que no necesite

  @EliminarPost
  Scenario Outline: Eliminar un post de PlaceHolder
    Given que el usuario ingresa a la API de PlaceHolder en la seccion de Eliminar Post
    When el usuario ingresa  el <id> del post que desea eliminar
    Then el usuario debe un  <status> de la peticion

    Examples:
      | status | id |
      | 200    | 2  |
      | 200    | 3  |
      | 200    | 4  |
      | 200    | 5  |