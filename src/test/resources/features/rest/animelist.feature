Feature: Obtener anime por id
  Yo como usuario de la API de Anime Jikan
  Quiero obtener un anime por su id
  Para poder obtener información de un anime en específico

  @ObtenerAnimePorId
  Scenario Outline: seleccion anime por id
    Given  que el usuario esta usando la API de Anime Jikan
    When  se envia una peticion para obtener un anime por su <id>
    Then  se deberia observar el <estatusHttp>  junto con la  <url> del anime y el <mal_id> del anime
    Examples:
      | id      | estatusHttp | url                                            | mal_id |
      | "1"     | 200         | "https://myanimelist.net/anime/1/Cowboy_Bebop" | 1      |
      | "6"     | 200         | "https://myanimelist.net/anime/6/Trigun"       | 6      |
      | "10000" | 404         | "Not found"                                    | 1      |
      | "A"     | 404         | "Not found"                                    | 1      |

