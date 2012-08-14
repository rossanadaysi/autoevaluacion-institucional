-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 13-08-2012 a las 11:14:30
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `institucional2`
--

--
-- Volcado de datos para la tabla `ponderacionfactor`
--

INSERT INTO `ponderacionfactor` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES
(1, 13, 'Representa la filosofía y la razón de ser de la Institución, marca el direccionamiento estratégico para el logro de los objetivos  y propósitos misionales que contribuyen al alcance de la Visión, en concordancia con los principios, valores y la política de calidad.', 2, 1),
(2, 12, 'Los estudiantes y profesores son protagonista de la dinámica académica de la Institución y son los pilares fundamentales  para la puesta en marcha de los procesos misionales.', 2, 2),
(3, 11, 'Los procesos académicos claramente definidos garantizan la calidad de la formación, en la cual la interdisciplinariedad, la flexibilidad y la evaluación curricular son coherentes con la formación integral de los estudiantes.', 2, 3),
(4, 11, 'La investigación es uno de los procesos misionales,  contribuye a la formación de profesionales competentes  con el fin de generar conocimiento dirigido  a la transformación social,  consolidando comunidades académicas de calidad, articulando  la docencia y la extensión en beneficio de la sociedad.', 2, 4),
(5, 10, 'La Universidad integra el proceso misional de extensión  para  responder a las necesidades locales, regionales, nacionales e internacionales, a través de la formación de profesionales competentes e integrales  y  genera soluciones que la comunidad requiere con responsabilidad social.', 2, 5),
(6, 8.5, 'Permite el desarrollo de actividades participativas que integran a la comunidad universitaria para medir los diferentes procesos, de manera que asegure consolidar la cultura del mejoramiento continúo en cumplimiento de la política de calidad.', 2, 6),
(7, 9.5, 'Las políticas de bienestar ayudan a generar un clima institucional para contribuir al desarrollo de los procesos misionales,  generando calidad de vida, convivencia armónica y optimización de los recursos.', 2, 7),
(8, 9, 'Define lineamientos, procedimientos y mecanismos de comunicación en la comunidad universitaria, para la consecución eficaz, eficiente y efectiva del producto de los procesos misionales.', 2, 8),
(9, 8, 'La Institución propende por la búsqueda de ambientes físicos y de recursos académicos  óptimos para la formación y la prestación de servicios, e incorpora la tecnología adecuada en el quehacer universitario en todos los niveles y modalidades.', 2, 9),
(10, 8, 'El patrimonio con que cuenta la institución, su liquidez, solidez financiera y manejo eficiente del presupuesto, garantiza que se  asignen equitativamente los recursos económicos para el cumplimiento de todos sus procesos.', 2, 10);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
SELECT facultad.nombre AS 'FACULTAD', programa.nombre AS 'PROGRAMA POSTGRADO', COUNT( * ) AS 'POBLACION', ROUND( (
count( * ) * 0.06193092419200908 ) * 1.4, 0
) AS 'MUESTRA'
FROM estudiante
INNER JOIN programa ON estudiante.programa_id = programa.id
INNER JOIN facultad ON programa.facultad_id = facultad.id
WHERE programa.descripcion = 'Postgrado'
GROUP BY estudiante.programa_id
ORDER BY `facultad`.`nombre` ASC
LIMIT 0 , 30


SELECT facultad.nombre AS 'FACULTAD', programa.nombre AS 'PROGRAMA POSTGRADO', programa.descripcion, COUNT( * ) AS 'POBLACION', IF(programa.descripcion = 'Pregrado', ROUND( (count( * ) * 0.06193092419200908 ) * 1.4, 0
), (ROUND( (count( * ) * 0.06193092419200908 ) * 1.4, 0)+3)) FROM estudiante INNER JOIN programa ON estudiante.programa_id = programa.id INNER JOIN facultad ON programa.facultad_id = facultad.id GROUP BY estudiante.programa_id ORDER BY `facultad`.`nombre` ASC, programa.descripcion


       
                             