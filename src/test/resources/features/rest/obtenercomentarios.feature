Feature: Obtener comentarios de un posts
  AS usuario de la pagina de JsonPlaceHolder
  WANT Consultar y obtener los comentarios posts
  BECAUSE Leer las opiniones del post en la pagina

  @ComentariosPost
  Scenario Outline: Buscar comentarios en un post por id
    Given El usuario se encuentra en la web de JsonPlaceHolder para realizar la consulta
    When El usuario envia una solicitud con el <id> del post del cual se quieren los comentarios
    Then El usuario debe recibir un respuesta de status <code> y los comentarios del post
    Examples:
      | id    | code |
      | "1"   | 200  |
      | "2"   | 200  |
      | "3"   | 200  |
      | "150" | 200  |
      | "0"   | 200  |