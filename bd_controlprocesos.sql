-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-04-2024 a las 23:48:11
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_controlprocesos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenservicio`
--

CREATE TABLE `ordenservicio` (
  `id` int(11) NOT NULL,
  `nombreOS` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `procesoActual` varchar(20) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ordenservicio`
--

INSERT INTO `ordenservicio` (`id`, `nombreOS`, `fecha`, `procesoActual`, `estado`, `idUsuario`) VALUES
(1, 'OS-003', '2024-04-05', 'TO DO', 'activo', 1),
(2, 'OS-004', '2024-04-05', 'TO DO', 'activo', 1),
(3, 'OS-007', '2024-04-05', 'IN PROCESS', 'activo', 1),
(4, 'OS-009', '2024-04-05', 'DONE', 'activo', 1),
(5, 'OS-001', '2024-04-05', 'DONE', 'activo', 1),
(6, 'OS-005', '2024-04-05', 'DONE', 'activo', 1),
(7, 'OS-008', '2024-04-05', 'DONE', 'activo', 1),
(8, 'OS-002', '2024-04-05', 'DONE', 'activo', 1),
(9, 'OS-006', '2024-04-05', 'DONE', 'activo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proceso`
--

CREATE TABLE `proceso` (
  `id` int(11) NOT NULL,
  `nombreProceso` varchar(20) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `motivo` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(10) NOT NULL,
  `idOS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proceso`
--

INSERT INTO `proceso` (`id`, `nombreProceso`, `tipo`, `motivo`, `fecha`, `estado`, `idOS`) VALUES
(5, 'ARMADO', 'IN PROCESS', '', '2024-04-05', 'activo', 1),
(6, 'ARMADO', 'IN PROCESS', '', '2024-04-05', 'activo', 4),
(15, 'ARMADO', 'IN PROCESS', '', '2024-04-05', 'activo', 3),
(16, 'ARMADO', 'IN PROCESS', '', '2024-04-05', 'activo', 4),
(17, '', 'UNPLANNED DOWNTIME', 'Maquina no disponible', '2024-04-05', 'parado', 3),
(18, '', 'REPROGRAMACION', 'Motivos internos', '2024-04-05', 'reprogram', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombreUsuario` varchar(100) NOT NULL,
  `contrasenia` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombreUsuario`, `contrasenia`) VALUES
(1, 'usuario_001@gmail.com', 'abc123'),
(2, 'usuario_002@gmail.com', 'abc123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ordenservicio`
--
ALTER TABLE `ordenservicio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proceso`
--
ALTER TABLE `proceso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idOS` (`idOS`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ordenservicio`
--
ALTER TABLE `ordenservicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `proceso`
--
ALTER TABLE `proceso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
