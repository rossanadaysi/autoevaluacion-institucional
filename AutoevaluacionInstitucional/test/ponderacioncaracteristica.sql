-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 13-08-2012 a las 11:14:10
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
-- Volcado de datos para la tabla `ponderacioncaracteristica`
--

INSERT INTO `ponderacioncaracteristica` (`id`, `nivelimportancia`, `ponderacion`, `justificacion`, `proceso_id`, `caracteristica_id`) VALUES
(1, 10, 4.73, 'Refleja el deber ser de la institución de acuerdo con su naturaleza, orienta los procesos misionales, define los compromisos con la calidad y responde a las necesidades de la comunidad y a su compromiso social, acogiéndose a los principios constitucionales y a los objetivos de la educación superior. ', 2, 1),
(2, 9, 4.25, 'Establece los lineamientos acerca de la planeación, la administración, la evaluación y los articula con los procesos misionales, de apoyo y estratégicos', 2, 2),
(3, 8.5, 4.02, 'El proyecto institucional involucra estrategias para la formación integral que apunta a la transformación social y contribuye a la pertinencia y al impacto social.', 2, 3),
(4, 8, 1.37, 'Un reglamento estudiantil claro y concertado se constituye en la base que garantiza la convivencia en la institución. Este documento consigna los deberes, derechos y normas disciplinarias que debe ser conocido por la comunidad universitaria.', 2, 4),
(5, 10, 1.71, 'Un proceso de admisión transparente equitativo y con criterios académicos definidos, garantiza la igualdad de condiciones de acceso de todos los estudiantes a la institución, el seguimiento académico y una política de bienestar universitario garantiza la permanencia de los estudiantes en la institución.', 2, 5),
(6, 8, 1.37, 'El contar con estímulos y opciones de apoyo económico favorece la permanencia de los estudiantes con méritos académicos.', 2, 6),
(7, 8, 1.37, 'La existencia de normas claras que orientan acerca de los procesos que se deben seguir para la vinculación, permanencia  desarrollo de los docentes permiten que los procesos de selección respondan a unos criterios de calidad y pertinencia y a un trato  equitativo en el desempeño laboral.', 2, 7),
(8, 10, 1.71, 'Son los docentes quienes intervienen directa y principalmente en el desarrollo de los procesos misionales, por lo cual la institución debe contar con una planta profesoral adecuada en número y formación.', 2, 8),
(9, 8, 1.37, 'La carrera docente es un mecanismo para el desarrollo y bienestar de los profesores, estimula su crecimiento fundamentado en su producción académica, además brinda estabilidad laboral', 2, 9),
(10, 9, 1.54, 'Promueve la formación de alta calidad y categoría que se refleja en la transformación social y en la mejora de indicadores institucionales.', 2, 10),
(11, 9, 1.54, 'Permite internacionalizar los procesos académicos, mantiene la vigencia de los programas propiciando la movilidad estudiantil, profesoral, transferencia de conocimiento y formación de redes académicas.', 2, 11),
(12, 10, 5.79, 'Permite optimizar recursos con perfiles abiertos para todos los estudiantes de la institución y específicos para áreas comunes; además de actualizar propuestas curriculares de acuerdo con la dinámica del mundo globalizado, la tendencia del conocimiento y de los avances tecnológicos.', 2, 12),
(13, 9, 5.21, 'La oferta de programas académicos es coherente  con las necesidades del entorno,  articula los niveles formación permitiendo el desarrollo personal y de la sociedad.', 2, 13),
(14, 9, 5.82, 'Permite la formación investigativa y su articulación en todos los niveles, promueve el desarrollo de jóvenes investigadores, a través de los semilleros y de la formación de investigadores en concordancia con el desarrollo de las ciencias, las tecnologías, las artes y la filosofía.', 2, 14),
(15, 8, 5.18, 'La investigación genera conocimiento, transforma y aprovecha los avances científicos y tecnológicos como factor de desarrollo social y económico, en busca de una mayor calidad de vida.', 2, 15),
(16, 9, 3.6, 'Desarrolla programas y actividades que inciden en la calidad de la educación y promueve la interacción con la comunidad académica y científica, sector productivo y gubernamental.', 2, 16),
(17, 8, 3.2, 'Los egresados retroalimentan los procesos, participan en el proceso de toma de decisiones en los diferentes estamentos que conforman y permiten el posicionamiento y reconocimiento institucional en todos los niveles', 2, 17),
(18, 8, 3.2, 'La integración de los procesos misionales facilita y garantiza el logro de los objetivos institucionales.', 2, 18),
(19, 9, 3.06, 'La evaluación permanente favorece el seguimiento a los procesos misionales estratégicos y de apoyo, para generar acciones correctivas y preventivas en procura de la cultura del mejoramiento continuo.', 2, 19),
(20, 8, 2.72, 'El manejo y sistematización de los datos facilita el análisis y permite la toma efectiva de decisiones, así como detectar a tiempo desviaciones y proceder a su corrección inmediata.', 2, 20),
(21, 8, 2.72, 'Es importante aplicar permanentemente sistemas de evaluación a los tres estamentos para favorecer el mejoramiento continuo con el fin de garantizar el buen desarrollo de los procesos misionales, estratégicos y de apoyo con calidad.', 2, 21),
(22, 8, 3.45, 'Unas políticas de bienestar institucional, claramente definidas favorecen que la comunidad académica cuente con condiciones adecuadas para su desarrollo integral y permitan la interacción, cooperación y trabajo armónico para el logro de objetivos comunes.', 2, 22),
(23, 7, 3.02, 'Unas políticas de bienestar institucional, claramente definidas favorecen que la comunidad académica cuente con condiciones adecuadas para su desarrollo integral y permitan la interacción, cooperación y trabajo armónico para el logro de objetivos comunes.', 2, 23),
(24, 7, 3.02, 'La existencia de recursos permite el cumplimiento de planes y acciones establecidas para el logro del bienestar institucional, favoreciendo las condiciones físicas y mentales de la comunidad académica.', 2, 24),
(25, 8, 2.25, 'Las políticas de administración y gestión deben estar orientadas al cumplimiento y desarrollo de los procesos misionales coherentes con la naturaleza de la institución.', 2, 25),
(26, 7, 1.97, 'Los canales de comunicación al interior de la institución, permiten una adecuada motivación y capacitación continúa de la comunidad académica, favoreciendo el trabajo en equipo,  flujo oportuno de información y el cumplimiento de los objetivos comunes.', 2, 26),
(27, 9, 2.53, 'La capacidad de gestión facilita el logro de los objetivos trazados por la institución, permite direccionar  las políticas establecidas en el plan de desarrollo institucional y lograr estabilidad administrativa.', 2, 27),
(28, 8, 2.25, 'Unas políticas y procedimientos claros para la creación y modificación de programas, mantienen a la Universidad vigente, actualizada y respondiendo a las necesidades en un contexto global.', 2, 28),
(29, 8, 4, 'La disponibilidad y el uso adecuado de los recursos técnicos, tecnológicos y talento humano, permiten contar con un acceso a información especializada de todos los niveles para el fortalecimiento y el desarrollo de la comunidad académica en sus procesos misionales.', 2, 29),
(30, 8, 4, 'Los espacios físicos adecuados, suficientes y seguros, garantizan la realización de actividades académicas, recreativas y deportivas que apoyan la formación integral, el desarrollo personal y laboral de la comunidad institucional.', 2, 30),
(31, 8, 1.88, 'El patrimonio,  la solidez financiera, la integridad en el manejo presupuestal y la asignación equitativa de los recursos son la base para el cumplimiento con calidad de la función social de la Universidad.', 2, 31),
(32, 8, 1.88, 'El cumplimiento de políticas claras en la elaboración, ejecución y evaluación presupuestal garantizan el desarrollo de los procesos académicos y administrativos institucionales y proporciona los indicadores financieros que permiten establecer correctivos', 2, 32),
(33, 9, 2.12, 'La planeación en el uso de los recursos financieros que responda a las necesidades del plan de desarrollo institucional y su manejo transparente, permite el logro de los procesos misionales.', 2, 33),
(34, 9, 2.12, 'El equipo eficiente para la organización del manejo financiero, conocedor de sus funciones, así como los procesos de apoyo, sus documentos y sus formatos son garantía para una ejecución presupuestal coherente con lo proyectado en el plan de desarrollo.', 2, 34);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
