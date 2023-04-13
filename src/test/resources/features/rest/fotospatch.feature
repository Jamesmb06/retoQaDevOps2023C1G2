Feature: Editar titulo y album de foto
  AS  administrador  de API de jsonplaceholder
  I WANT TO
  crear post
  SO THAT
  I editar titulo y albumId de foto

  @Patch
  Scenario Outline: editar informacion de foto
    Given el administrador esta en la pagina para editar informacion de la foto
    When cuando envia solicitud con <id> de foto nuevo nombre de albumId <albumId> titulo y  <title>
    Then la pagina retornara un estatus con codigo <codigo> y con el nuevo albumId <albumId> titulo y  <title>


    Examples:
      | id  | albumId | title          | codigo |
      | "1" | "1"     | "Libros "      | 200    |
      | "2" | "3"     | "free"         | 200    |
      | "7" | "10"    | "Titulo nuevo" | 200    |