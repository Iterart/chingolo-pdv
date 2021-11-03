/* Roles */
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_CAJERO');

/* Usuarios - en las pruebas todas las claves son 'usuario' */
INSERT INTO `usuarios` (`activo`, `email`, `password`, `token_exp`, `rol_id`) VALUES ('1', 'usuario', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', '0', '1');
INSERT INTO `usuarios` (`activo`, `email`, `password`, `token_exp`, `rol_id`) VALUES ('1', 'invitado', '$2a$10$nmsnELze.Ca7dMnsbfGIuuczJlKMAk9SGCkgDczmosj91zCAMsFoO', '0', '2');

INSERT INTO categorias (cat) VALUES ('Alimenticios');
INSERT INTO categorias (cat) VALUES ('Bebidas');
INSERT INTO categorias (cat) VALUES ('Indumentaria');
INSERT INTO categorias (cat) VALUES ('Perfumería');
INSERT INTO categorias (cat) VALUES ('Electricidad');
INSERT INTO categorias (cat) VALUES ('Ferretería');
INSERT INTO categorias (cat) VALUES ('Construcción');

INSERT INTO `iterart_kiosco`.`productos` (link_img, `act`, `descr`, `precio_costo`, `precio_especial`, `precio_venta`, `stock`, `stock_min`, `categoria_id`) VALUES ('https://jumboargentina.vteximg.com.br/arquivos/ids/548372-1000-1000/Arroz-Apostoles-Parboil-X1kg-1-714769.jpg?v=637043734158500000', 1, 'Arroz Apóstoles 1kg', '59.99', '89', '110', '50', '20', '1');
INSERT INTO `iterart_kiosco`.`productos` (link_img, `act`, `descr`, `precio_costo`, `precio_especial`, `precio_venta`, `stock`, `stock_min`, `categoria_id`) VALUES ('https://www.casa-segal.com/wp-content/uploads/2020/03/coca-cola-225L-almacen-gaseosas-casa-segal-mendoza.jpg', 1, 'Coca Cola 2.25lts', '159.99', '180', '200', '75', '25', '1');
INSERT INTO `iterart_kiosco`.`productos` (link_img, `act`, `descr`, `precio_costo`, `precio_especial`, `precio_venta`, `stock`, `stock_min`, `categoria_id`) VALUES ('https://ardiaprod.vteximg.com.br/arquivos/ids/187401-1000-1000/Cerveza-Patagonia-Session-Ipa-en-Lata-473-ml-_1.jpg?v=637427605351170000', 1, 'Cerveza Patagonia IPA Lata x 6', '879.89', '960', '1050.99', '15', '5', '1');
