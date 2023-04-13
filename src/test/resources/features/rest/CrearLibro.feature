Feature: Crear un libro
  Como: Un usuario de la API Fake REST
  Quiero: Crear un nuevo libro
  De manera que

  @CreateBook
  Scenario Outline: Crear un nuevo libro
    Given Tengo acceso al servidor de la API de Fake REST
    When Trato de crear un libro con ID <id>, titulo '<title>' y numero de paginas <pageCount>
    Then Vere un codigo de respuesta <code>
    And Recibire la informacion del libro de vuelta
    Examples:
      | id  | title          | pageCount | code |
      | 1   | Las rosas      | 12        | 200  |
      | -1  | Cara dura      | 69        | 200  |
      | 100 | Flecha         | 44        | 200  |
      | 101 | Por la Ventana | 188       | 200  |