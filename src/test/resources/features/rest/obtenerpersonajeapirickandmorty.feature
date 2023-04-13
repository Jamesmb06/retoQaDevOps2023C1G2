Feature:Obtener personaje por id
  Yo como usuario de la API de Rick and Morty
  Quiero obtener la informacion de un personaje por su id
  Para poder obtener la informacion de un personaje en especifico

  @ObtenerPersonajePorIdRickAndMorty
  Scenario Outline: Seleccion personaje por id
    Given  que el usuario esta usando la API de Rick and Morty
    When el usuario envia una solicitud de tipo GET mandando el  <id>
    Then se deberia observar el estatus  <status>  y un  body de respuesta con la informacion del personaje
    Examples:
      | status | id       |
      | 200    | 2        |
      | 200    | 3        |
      | 200    | 4        |
      | 200    | 5        |