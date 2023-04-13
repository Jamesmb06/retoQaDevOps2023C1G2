Feature: Ver informacion de una baya pokemon
  yo como administrador de los servicios rest de la pokeApi
  quiero realizar peticiones al servicio de las bayas pokemon
  para ver toda la informacion acerca de una baya

  Scenario Outline: Ver informacion de una baya pokemon
    Given que estoy apuntando con un endpoint a la api de bayas de la pokeapi
    When envio la peticion get con el <idnombre> de la baya
    Then recibo un <codigo> de respuesta
    And la <informacion> acerca de la baya
    Examples:
      | idnombre | codigo | informacion |
      | "1"      | 404    | "jaboca"    |
      | "1"      | 404    | "cheri"     |
      | "1"      | 200    | "cheri"     |
      | "cheri"  | 200    | "cheri"     |
      | "63"     | 200    | "jaboca"    |
      | "jaboca" | 200    | "jaboca"    |
      | "65"     | 404    | "Not Found" |
      | "0"      | 404    | "Not Found" |