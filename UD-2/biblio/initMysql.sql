-- asegura que cada vez que se crea un contedor limpo, testdb e usuario estarán dispoñibles.

-- =========================================
-- Script de inicialización para MySQL
-- =========================================

-- Crear usuario de acceso remoto
CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';

-- Conceder permisos sobre a base de datos biblioYaniraV
GRANT ALL PRIVILEGES ON biblioYaniraV.* TO 'usuario'@'%';

-- Conceder permisos para crear bases de datos
GRANT CREATE ON *.* TO 'usuario'@'%';

-- Activar os cambios
FLUSH PRIVILEGES;