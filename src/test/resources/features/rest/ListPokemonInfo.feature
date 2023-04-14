Feature: Yo como usuario de la Poke API
  Quiero poder listar la informacion de
  los pokemones de determinada generacion

  @ListPokemons
  Scenario Outline: Listar informacion de manera exitosa
    Given Que estoy en la Poke API
    When Hago una peticion para listar pokes de generacion <gen>
    Then Debo ver un codigo de estado exitoso
    And La respuesta debe contener un body con toda la info <id> y <main_region>
    Examples:
      | gen | id  | main_region |
      | "1" | "1" | "kanto"     |
      | "2" | "2" | "johto"     |