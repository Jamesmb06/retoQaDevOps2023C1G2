Feature: Buscar personajes
  AS  usuario de rickandmortyap
  I WANT TO
  obtner personajes por id
  SO THAT
  I ver registro completo del personaje

  @Get
  Scenario Outline: buscar personajes
    Given el usuario esta en la pagina de busqueda
    When cuando el usuario envia solicitud de busqueda  por id <id>
    Then la pagina retornara un estatus con codigo <codigo> y nombre <nombre>
    Examples:
      | id     | nombre         | codigo |
      | "1"    | "Rick Sanchez" | 200    |
      | "4"    | "Rick Sanchez" | 200    |
      | "4"    | "Rick Sanchez" | 200    |
      | "1000" | "Rick Sanchez" | 404    |