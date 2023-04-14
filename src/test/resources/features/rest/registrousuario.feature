Feature: registro de usuarios
  AS  usuario de reqres
  I WANT TO
  registrarme en el sistema
  SO THAT
  I poder usuar servicios de reques

  @Register
  Scenario Outline: Registro de usuarios
    Given El usuario esta en la pagina de registro.
    When el usuario envia una solicitud de registro con el <email> y la <password>
    Then el usuario ve un codigo de respuesta de estado <code> y una identificacion con un token
    Examples:
      | email                    | password     | code |
      | "eve.holt@reqres.in"     | "pistol"     | 400 |
      | "george.bluth@reqres.in" | "cualquiera" | 400  |