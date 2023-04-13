Feature: Actualizar informacion de los usuarios
  yo como administrador de los servicios rest de reqres.in
  quiero realizar peticiones al servicio de actualizar un usuario
  para modificar los atributos de los usuario

  Scenario Outline: Actualizar informacion de un usuario
    Given que estoy apuntando con un endpoint a la api de reqres.in
    When envio la peticion put con el <id> de el usuario el <nombre>,<edad> y <trabajo>
    Then se recibe un <codigo> de respuesta
    And la informacion actualizada del usuario con la fecha de actualizacion
    Examples:
      | nombre   | edad | trabajo      | codigo | id   |
      | "Juan"   | 20   | "QA"         | 404    | "1"  |
      | "Juan"   | 20   | "QA"         | 404    | "-1" |
      | "Camilo" | -1   | "Profesor"   | 404    | "4"  |
      | "Pedro"  | 21   | "Secretario" | 200    | "2"  |
      | "Fabio"  | 22   | "Doctor"     | 200    | "3"  |
      | "Ash"    | 14   | "Entrenador" | 200    | "5"  |