Feature: Agregar una nueva foto a un álbum
  Yo como usuario de la API de JsonPlaceholder
  Quiero poder agregar fotos a los albunes

  @NewPhoto
  Scenario Outline: Agregar una nueva foto a un álbum existente
    Given que tengo un album con id <albumId>
    When agrego una nueva foto con los siguientes datos <title>, <url>,<thumbnailUrl>
    Then la respuesta de la peticion debe tener el codigo de estado <statusCode>
    And la respuesta debe incluir los datos de la nueva foto:

    Examples:
      | albumId | title          | url                                      | thumbnailUrl                             | statusCode |
      | "2"     | "Nueva foto 2" | "https://via.placeholder.com/600/92c952" | "https://via.placeholder.com/150/92c952" | 201        |
      | "1"     | "Foto 3"       | "https://via.placeholder.com/600/92c952" | "https://via.placeholder.com/150/92c952" | 201        |
      | "3"     | "Foto 4"       | "https://via.placeholder.com/600/92c953" | "https://via.placeholder.com/150/92c952" | 201        |


