Feature: Actualizacion Exitosa de comentarios
  Como usuario de la pagina json placeholder
  Quiero realizar una actualizacion a los comentarios actuales del sistema
  Para verificar el correcto funcionamiento del servicio

  @ActualizarComentarios
  Scenario Outline: Actualizacion exitosa de comentarios
    Given que el usuario esta en la pagina de json placeholder
    When el usuario envia una solicitud de actualizacion con el <id> el "<name>" el "<body>" y el "<email>"
    Then el usuario ve un codigo de respuesta de estado <codigo>
    Examples:
      | id | name        | body                                                  | email                 | codigo |
      | 1  | Bryan1234   | Excelente receta                                      | david2233@hotmail.com | 200    |
      | 2  | AngiieGamer | Te falto explicar un poco mejor                       | angie223@gmail.com    | 200    |
      | 3  | Majito2456  | Baja el volumen de la musica de fondo para la proxima | majo1144@hotmail.com  | 200    |