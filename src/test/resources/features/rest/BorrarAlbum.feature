Feature: Borrar un album
  Como: Un usuario de JSONPlaceholder
  Quiero: Borrar un album en particular
  De manera que

  @DeleteAlbum
  Scenario Outline: Borrar varios albums
    Given Tengo acceso al servidor de la API JSONPlaceholder
    When Trato de borrar un album con ID <id>
    Then Vere el codigo de respuesta <code>
    Examples:
      | id  | code |
      | 1   | 200  |
      | -1  | 200  |
      | 100 | 200  |
      | 101 | 200  |