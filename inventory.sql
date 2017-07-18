CREATE DATABASE  IF NOT EXISTS `inventory` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `inventory`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: inventory
-- ------------------------------------------------------
-- Server version	5.6.22-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `ACCOUNT_ID` int(5) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(20) COLLATE utf8_bin NOT NULL,
  `PASSWORD` varchar(20) COLLATE utf8_bin NOT NULL,
  `ALIAS` char(20) COLLATE utf8_bin NOT NULL,
  `TYPE` char(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  UNIQUE KEY `username_UNIQUE` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'custodian','aics','haha','Administrator');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `BRANCH_ID` int(11) NOT NULL,
  `BRANCH` varchar(25) COLLATE utf8_bin NOT NULL,
  `ADDRESS` varchar(100) COLLATE utf8_bin NOT NULL,
  `TELEPHONE` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`BRANCH_ID`),
  UNIQUE KEY `Branch_UNIQUE` (`BRANCH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Commonwealth','aa','11234'),(2,'España','',''),(3,'Taytay','',''),(4,'Tanay','',''),(5,'Montalban','',''),(6,'Bacoor','',''),(7,'Dau','',''),(8,'Urdaneta','',''),(9,'Cogeo','',''),(10,'Marikina','dwwewe','');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;

--
-- Table structure for table `computers`
--

DROP TABLE IF EXISTS `computers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computers` (
  `PC_ID` int(11) NOT NULL,
  `IP_ADDRESS` varchar(16) COLLATE utf8_bin NOT NULL,
  `PC_NAME` varchar(25) COLLATE utf8_bin NOT NULL,
  `STATUS` char(15) COLLATE utf8_bin NOT NULL,
  `LOCATION` varchar(30) COLLATE utf8_bin NOT NULL,
  `BRANCH` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`PC_ID`),
  KEY `FK_COMP-LOC_LOC_LOC_idx` (`LOCATION`),
  KEY `FK_COMP-BRCH_BRCH_BRCH_idx` (`BRANCH`),
  CONSTRAINT `FK_COMP-BRCH_BRCH_BRCH` FOREIGN KEY (`BRANCH`) REFERENCES `branch` (`BRANCH`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_COMP-LOC_LOC_LOC` FOREIGN KEY (`LOCATION`) REFERENCES `location` (`Locations`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computers`
--

/*!40000 ALTER TABLE `computers` DISABLE KEYS */;
INSERT INTO `computers` VALUES (101,'221','214','Working','Computer Laboratory','Taytay');
/*!40000 ALTER TABLE `computers` ENABLE KEYS */;

--
-- Table structure for table `equipments`
--

DROP TABLE IF EXISTS `equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipments` (
  `ITEM_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `ITEM_NAME` varchar(45) COLLATE utf8_bin NOT NULL,
  `IS_AVAILABLE` varchar(10) COLLATE utf8_bin NOT NULL,
  `LOCATION` varchar(30) COLLATE utf8_bin NOT NULL,
  `BRANCH` varchar(25) COLLATE utf8_bin NOT NULL,
  `LAST_BORROWED_BY` char(10) COLLATE utf8_bin DEFAULT NULL,
  `DATE_ARRIVED` date NOT NULL,
  `DATE_LB` date NOT NULL,
  PRIMARY KEY (`ITEM_ID`),
  KEY `FK_LBB_IC_idx` (`LAST_BORROWED_BY`),
  KEY `FK_BRCH_BRCH_idx` (`BRANCH`),
  KEY `FK_LOC_LOC_idx` (`LOCATION`),
  CONSTRAINT `FK_BRCH_BRCH` FOREIGN KEY (`BRANCH`) REFERENCES `branch` (`BRANCH`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_LBB_IC` FOREIGN KEY (`LAST_BORROWED_BY`) REFERENCES `facultystaff` (`ICODE`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_LOC_LOC` FOREIGN KEY (`LOCATION`) REFERENCES `location` (`Locations`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipments`
--

/*!40000 ALTER TABLE `equipments` DISABLE KEYS */;
INSERT INTO `equipments` VALUES ('5514111','Preda','Yes','Room 201','Taytay','JMR','2015-02-17','2015-02-19');
/*!40000 ALTER TABLE `equipments` ENABLE KEYS */;

--
-- Table structure for table `facultystaff`
--

DROP TABLE IF EXISTS `facultystaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facultystaff` (
  `EMPLOYEE_ID` varchar(15) COLLATE utf8_bin NOT NULL,
  `ICODE` char(10) COLLATE utf8_bin NOT NULL,
  `LAST_NAME` char(20) COLLATE utf8_bin NOT NULL,
  `FIRST_NAME` char(20) COLLATE utf8_bin NOT NULL,
  `M_INIT` char(4) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` varchar(100) COLLATE utf8_bin NOT NULL,
  `CONTACT_NUM` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `E_MAIL` varchar(35) COLLATE utf8_bin DEFAULT NULL,
  `IMAGE` blob,
  PRIMARY KEY (`EMPLOYEE_ID`),
  UNIQUE KEY `ICODE_UNIQUE` (`ICODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultystaff`
--

/*!40000 ALTER TABLE `facultystaff` DISABLE KEYS */;
INSERT INTO `facultystaff` VALUES ('10110011','JDC','Java','Computer','D','Binary','1001101101','10010111@1011.com','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0–\0\0\0–\0\0\0<qâ\0\0\0 cHRM\0\0z%\0\0€ƒ\0\0ùÿ\0\0€é\0\0u0\0\0ê`\0\0:˜\0\0o’_ÅF\0\0\0	pHYs\0\0Ã\0\0ÃÇo¨d\0\0\"¤IDATx^í]	x”åµž}ÏÌ$3Ù3Ù$d!û*AdßdUTE©X¥–ªÔª(BAE\\¨U«­·×Þ^µ­­ÞÛúôÚ>×zÕV¯VÔ^esÎ}Ï73aHgÏd&ožç<ÉŸüßÌ?çÿsÎwÎ{¾O¡/©©©©©©©©©©©©©©©©©©©©©©©©©©©©©> 3®q\0¤2	2²²TJÂu°:_™qAT}\0G\n.²²ò<ämÈ	±¨Õj2\Z¤ÑhH­Q“N§#ŽUji´\ZÒéõd0Ä±V«%=Žù§åsñ?96V]A¯*•¸~ò~r\0²RÑÛ@6tä-¾h«ÕJC*+¨|H?Š6\\µžf_:‹\ZF×Ñ‚åóiÓ–«iÜô±ÔÔ>‚V¬[N®^OÍí4fJ]±i-]½„êFÕÒ´9Sèš-›hÖ¥áx8Í[2×;¶]ŽVWk—Ñº+×àÔS=t;óâ4vÂrç‘5Í*XÜ»3W «!iÉY|ø>¶JV›•Fµ¤ÖöfZ¸jÝuß÷iþò¹TÝ<ŒfÌ›J/¢Ò!ÅÔ¯²„Ú¦¢–	”Û/‹Õ–Ó„Ùã¨ªy(å–fSuK%Mî˜@ªúQAY5Ž«§q3ÇRAyž/ÇÆ¨«áe4©c<5´ÕRáÀ|j\Zß@K×-¦©s\'Ñ ºrª=œZÚš(+7ËgÑÞÁ}½Â^(¡/Ž“>u8T×X]BwÝ¿Ve\0’C…ƒ\\”70‹´iJR¤(H›£ ½KEÊT˜â4é\n!yøÝIW¡&>Ûs¬ÂOC¡ŠTžc\rÎÓ(I‰qbl‹®”Ð§±aô«„^M.-åUfQZ‘2KœT?n8ÍY2K³ÝLJîBñ;Hm\"¥Ã‡|Ÿ]ž«ÈEÛo¿•n½ãÛÔ¯¢„²Š3(µ0…Ô\0\0`R¹\0ˆiÃ|±Jü­Ò_lŽ+<ÿ7ÄqŽË=Çüwþ?Ÿ§+ÆïC=Â¿Ë±1êŠïë™õZæy`µ¸?è\\‹{¥ÆžS™NM“ëhàˆþ”æ²‘J+â²¯ k{\\éxó5j\r]ºh>-[¿”\ZÇ4P¿a%dÌÔ’Â‚‹ÃÅ\Zy\0¢/Q¹\ZRïùsƒçØ€/Äà1À­Á1\rÀ²ð1¾¸¡?þWëù¿a°w,ÆÉ±ñÑ•Ð;T~HÍÐ¿	ºf# ÀRk© .›²;ÉVh&µ±3èßÚàÊÂ›¾ÁA^ó˜&Ú~ç-4iÖRš`l°$¥\0\nž_° z?€„×p¯%¸Ìx/þÒrlà1žºbëÅz®ó<Ä|tÞ‡ÜQm¢´2¥c&oí×Mñ—\roöšÙl¦-7m¡%k‘3/™Hä{Ì([(¬[áª¼\0a±5ê´>l¹`†Z®.\n“c“£+#{¾G°b¶áZ²–éÉT¬%•µ3UÁA}\\^‡9¯ÔØ6‚®Ø¼jZ†‘¢‚O6!fâ8Çµz¢b—°\\rlDbÜuÅ¡Âk­š,eZÒaeî×¨î\"ëž,_µŒ.¿v=¥¤›I@ñÍæ8(RëÓõK·¡å’c“¬+W½šÌ1£ÄÄJ©àâtDÌ¹®á|*#7ÖmZC#\'µ.Ý“q\\_§Ù”K×ÖçÃ€N À¥B¦$\rRG\n¥\0×ÎX¬§~›››K×nÝD¥ƒ‹IçDLå\rÒÏ™µuÃúäØ³³ã^B°!I©Wß3ÒIÀÇIHU´àâ(ŸÖN›n¼Š²ûgÉMF°˜ñu‰}¤kóKŸ„H_Öƒ‹ã.ž9z­Ö¢–\'¿UÚ¿”ÆÏG9(½ðì“krÆ\'g¸\0Ë„ô„š+#žúbu¤àšÍÖjåº•´|ýÒØáW,ÞÌÔËÍu$iŒ¸Ïžbœ„ôé0\0àÒ#Á­PpíŽXÏ˜ÌF\Z7c,U·Vzêz(¥tfÊÏcS0û²këÎ÷VË)€uÂU™¯<ü÷Xë˜\Z3u4ijÒs>#L®J&3#O	œºÙ\0®ë¢ÄÞ\rÂ1yÈ×\\\\AÓ.™„J¸’t(Óø‚6™=[U¸Ð]*ƒ‹ƒx¥Q\0ë™pÀº×h2 »^Eeõ¥¢\nÎÈZ@–nñœÊÀ…äÙØ°7S‚|\0P}±—ÿx¥t@	ÈucD-LN/Dhž¦^~ßà1µÿýåÚ°&³svÈLâ€¯Tüõ£QcGÒÂµóÉVj\"\r÷X\nÈqwÁ(7‘ÌR/´±	š¥ú¨NZðë¼q7Ì|õG]ðLnQµLA¶!zÒsÖ•¾ã…Gê&, ÍNC¦s°ÅdÎ´¦vN®Áâ 8àâk4ûu!ŽMV-U$ÉQ‰a—Ò“vØXMŒ¼Ue”S–‰›¥7QÜäQ»E.0L»an\'f™u*˜§°žzf“2Ó”G\n||Ì@Ó2ÉÉal®×]áá3 §+$ê{ä#e2oŸËzWj°˜Yð5^©TÒøí4lL…çFá&uïbW¤nÑg™4Èh¡4\r2»*p³™2Ë×Â9H…Ì÷æB(S ùÿ\Z>7Ç(9©Á‰çsûÔX\\wÈkÆwßß­ë÷Ua¬y˜šRGéD™-Vé=ÌSÿði(13Ô†žŽS€\"Ó6i4œÝ(J8\\tö‘öarÙZ‰\'WÏU^ñÿÿÍq4çv}ïžëÉùÄUÔ`w–Ï) œqVÑWào¹\"\r?ˆÆÂ“ðDÇ›rx1˜Å\Z§Rª(-/•JêòÉZ£N8#”i²Þ\Z7ºÞ	¹Â\\ëóAnPhÛ-šƒ£®˜|töÆùT9¥NM!ÓD­G&y%Ô±÷æiZRÂjRTØžEM21‹·–+š±f¶\\9DŒåI9ü4(°øiª¨D¹™ ô4;D2óŠ1 ÷MëE±‚3vi¸îÉv¡}õïÅî‘m;éà/ž<ö³3ë~»Ž*ïG™Z(}¿	bŽJ2[H˜Òn³Qó‚Z²\r@\'93z}==ºx\'Y\"Æò$Iƒ‹-ÖäÙiø$OƒÂHÀ‘Ùôo”ðûÒþd29ŽÀE~\ré×WôO×íV¤7¹gn¼ç‹Cîï0Íxf2åÞŸJ©÷èÉq¯‘œ÷š¢–ô}èýÃíÊpÑ‚ó(«ÆN\Z<˜	]àv9Å®×Ë*\rm±ª›ª¨uöðÙ•¢ƒ#Xñ9\Z³ÙÕêuëÇq¬Ÿ7ÀâˆÐ­è7û›µwîÿüé¯îzë>j9\\Oé÷š¨b”oK3XEzžZ§4QQk6éyöœÈfÄá\"Æ2D`±2\n”S‘N)Õà9wíìA·èkãÞy,Rhnkíº“7?uðèótão¿E•—QÚ=!Ýõ\0áŠÎLCòÈVfý\"öñWÌ3¾p.•K:ø®%«ÂÅXÜ8QÝ:ŒŠ‡çª+ZpÅìùB1õ¶õmWH\nS¶»zÚ¶¯v½vèãŸÐe/­¢Ò½¹”zwì®¯+X*XŒ4s\Z¾¨…ì¨”píÎ—4®˜ï‘·÷ó†\".FI‡Ûÿ”ú,ÖŒ¹ÓhÄô\ZOÄaP·ˆ\'¡œ¯éÓ‹Î*÷ÄÕw}ùè{þöÍ~nåíNƒëÓÅO²n¾+×™K‹7, œº4Ò0oŽcŸ\0“®¸Ü#_wº¯³­#Ï\nÃ‹¤Åƒ‹¨~j5Yª@œg\Z!¸ºcr}_š•âM7ôM‹EŠÂi§—wß?žþâž?ï¡ÑO4QÆ.K·ã©`ÀÒV¨H­RSEÓ *lÎ\"ÊZ¢Üá¤+šb{À<&·\0žÍcÞXùX>(½,•,CQÒñÖébW¤&W˜oX+ž‰öI`	’®­zÕ©­|ò,Ýüú-Tu`9î1Æ%ž\n,5njŠ>…ú×–¹90Xf…FÃ›‹ôuMcð=Š`±‘ð1€ÕØÞ@åÍ¥b€¸ÐDÑføs`V½­E}Çb¹ÆTwÿ	7žøÁËò­ÿåzêÿÃ|ë°º¤/b,ÜØ[Mž?žå)¤å4N\"{<6aÓ\rl±¦vL¡–Ù\rÂ¤ÆZØŒÉ-ò²FœnàeúJMq•Ô¾bç—Þyäƒ#tñó³ÉµÛ‰ =¾ñT0‹¥\Z¨¤t{:]ºf¹\Z¤e×ÔšPa`½°ëe²_$1VFa:\ri+§”\Zt¾zó\">NV¬fÓ·’I0—ÊSdf„ì¯½Ä±Þo±H‘?á›Å7ïù¿§>¿ÿíýÔþäHÊÜ•â§zÎRùç±8ÆÒ¨4TT™O¹õN2òÈ·¨G–«;õ_A›á	âp±êP¨Ì;§ú+¥Œ²4\\ \'ÆJm†ý5O]yµ>oçGïÇSnEÅÒS›|äèO¾ÙþŸ;hø#CÄŒ/ù©H]\'»BŽ±¬+UŽL¦B-y1Ž}¢è0u¶ÈŸã£Í¨Â‹K:ÜS9f i™•@ÚŒ:¹}Û³\\Nï–[¡7»KÇ^wbçK‡>yž6¾¼‘Ê÷\n+•vo÷“ž‘‚Ê— å’NŽ#—f¯˜ICl¢¤ã[ë*\ZpÅºp‰Ži3°Zác,¦ÍLmfN£‡L—hÚÇXž^µÞ,,1Wæ¹pÇñý:ø¿ÏÐ¥?½„\nÀò˜qÎOE\n.ÅRQŠÉJÓL¡Â–LÒrz(‘Œ_¦Í°+Œ¤¤“4ÚÂÉ¶^YÒq+rÚÜó·ì>öÔ§{Þ9@ŽŒ¥¬û¬\"“)â}³!4¨ç¢V˜7(›Ò‡‚~’ˆÐEPÄc¡Íä%Ÿ6Ó;,Ó\rÝŠN_½çáOŸ9µãwRýcUä¼ÇÔ#IÏˆÁ·µÂ=H7àA´mÔ0q8YŠ@›á>€&ïR›½‘6S#i3ÌóÔÜy­WÜñÂá£/Ð¦W¯¡A– @G<‡\"rÄ êJ©¨œwØÛŒ¤@ÁÞ•ÚÌ:ÐfªA›ANIÒfp²z\rmÆ­H)v7Ì½íø¾?üèYZôâB*Ü“•üT8À9všÈ¾É@ÆvòGp…Ø\ZFÒfBÐ9z\rmv`¤»cÓ®c‡?Ú÷îc4åéñ”}¿½[¤¼p`	û¶R°\\ŽïÉ¶.¯´d³Zpç-V/m¦\\Òf›ëdÓf<¤¼sN¯¿ûÁÏž=yçï¦¦CÃAÊK~<å¼Kco7RÊ=iË5èŠQ‘eœãku‘öë4[ª¤Í„¤s$“6Chsp§Øxò¶g\"?uýo¶PÅCýzG<õ\0û&Yæè°›Ö€5iÈÚ‘qÜ¹Ùõk…^ùAnŽ¤Í„¥9\'…6ƒ=5òÝÕ³nùz÷ë‡>~Ž–ÿ|•ìÍñ¸¾,\"‡uìú8žº.nÖY·\"“Ž‰ÃšœÏìË²^Q:Ô7Ã¾Î[òIÚŒÿV\'~Ú¤ÑfÜŠŒ:÷Ô\rw;øáCï?Ž&‡)ž&‡82=#P s·#žÚ€xª®ƒtìá¸:ïCËtÇÏPTú@Å›Y>ÏÛÂIÚL(}¢i3nEÉ¬ÓkwîûÌÓäÐz¸2âÐä+Ä8Ò‘#Kûâ©åØ´ Ò¨ÈÔh?ã¼ÎõCSÊS\0ÓFˆoÝÏ#™9™’6’+”(ÚŒhrH«[{jÛ¡Éaëï¶ÑP498âÔä3°TOmC<5ñT¾\Z¥5Ygf|åØ”û¦n€þA\0j	¤¢÷¶˜ÉÈÌ´™¤ÓfÐäàpœºíë]¯þûó´ú—Qéó<®/ÉIOÇ÷O]‡xj’ŽTiˆ§œzJ]‘ýÔUY¿V;µ?\0.‚ð>Îj¿¾Å#\Z½¤Í¥s$„6ãV8jÎL^ƒ&‡wüíIO“ÃýñorˆÚbÁR9n7‘í\nZŸ2¨Ð!e&ÇU®R:œ?G|õm\0iÄ	áÅñü_Gl›¤Í„¤9÷$mMS¾Y¶}ïçG¾Üõç½ÔöD³§É!‰EäÎxê6#YW\"žªÄ¾Ì\Z%Ö_ñÔÿ˜Z­OAWCx‡Sk@ùäåçIÚL8&ª(éÄ“6#Hy¦ªU\'¶|ä“çèæ7n¥ênrˆÈbq<…¤gêÍF²\\¢\nñ”^M)Sœ_;6åýQ7Èø0³2j_æ#¼·¤Í„ª¸Ç›6ƒ&§»|Â\r\'îúw&åq“Ã€}ù\"é™ôüÇS[OMÓáAB<eÓ’}iö±ÔµÙ¯©3µ÷Lˆ&ˆ¥ê´X³AÒf‚1¬6Ó=Úš¹G/½óøÃo?úÁÓ4÷ùÊßž4Rž¿sÜxêJ=G!ž2a¹ó\"ÄXWä}l›þ’Ò¨ÜÄ`2±ÒN×x*ÆŽØvI›	¹Àr\\qYmÆ­pMâ&‡c?ò69Œ¢,nrñTÏ79u…œŸú.â©Ë°¶k•§Þg¨¶}ã¸ÎõžyŒ×I¿Ò\0±…±Rçïù…ù’6lKß¸¬6#HyÚÊ%§7ïø“g¿ùÎï¿GµV\n0±û‹(ö‰a9¡°ïë‹§¾¤ç‘KOqy’ó„óZ×[úJÓc@Ê*È`oÍëˆÞ i3!WAémMzw~Ûæw¼ô8Hy_¾’Ê÷{›’œŸ¤¼džx*Ed«Žl²¿H]Ÿó;užî> ˆw)‚h£A”÷Ü#›Å³ÚŒ¤Ía9ÆJ›A“Ã@49|ïø¾?=úái¯É¡7ÔûîD~5HycPD¶`µ\\XÏ\ryGm3þMiQ~à˜É‚¨b\09âÈpHÚLÜi3nEîè3s¯¿ÿØG÷¾óMM¶¤óÑ)o‡‰¬kOa\'x…¬µº›óß7·ÿ0º àåÏí1ªsVÈy,¹ÚLUP¢^mÆÛä°ðÔUøô¹S;ÞÜI\rÜä€xª»+å…™BÅa¾xêVÄS‹O\ràx\nØM;éØìú³±ÆrˆX©„˜º	*a±$m&³!*ÚŒ_“¯”wÍ¯®¥Áû¹É!>+åuXOm5yâ©l/)o^Æ—iWæ¾¡)ÐíæCJbŒ§áðˆ¤Í„[K Úš\nÜuo?þÀ›‡>zŽ–üË\"*â&‡$5v‚ë}LÊ»IÏvÄSVÄSÙ\0úºÜOm‹3_VZÕw\0S 9Ýˆ§KÒfÂ­dŽ6ãVd¶œéØtß±Ç?æ&‡©OO œûÐä€•]‚Z±®M\rHz2)o`ŠÛyMÞ–)i/ wÎkÕ·BÒâàúº¾…¤Í„Z©&äj3MîÞÿÙÓ\'Î69˜“ŸŸâ¤\'79,E<5ÐKÊ™vÊy½ë¿\rõ)O\0¼q3=Í=\0*OŒ%i3ÁWA	B›áXD¡p§6l8yË!?uýon !õïUMæ¿&‡9Çó~¯)1üW¾Ò¢ë!P	`IÚŒÿÂ¨Üâ¹´™ãŠŠ¡…îæq·|µû\rnrXñ¯ËÑäÜþ>ßŒ‹È›¹É¤<;“òO­ÊùÜ¾2ëU49ìÄ\rŸáýµýIy=/I›‰dó ¿ýŽ\ry±õ¶ûN?ùnr˜ùÌTïN=¿R^ØxŠIy—ƒ”×ˆüâ)Ý\049lr}h™áxùª›€žQGO (À{JÚLØ¾¼´øXjµæôm¿Úuj×»{Ñä0M£=³òpÄ©¿&«¯Éõ>S“ý´ãZ×;¦&«¯É¡7ß’ P	W(i3!v:ð§Í(°­œAo¢‡WRÝC{Q“HyóO0)OC)3ÒAÊs¡ÉÁÈMK!þM‰Â–¤Í„]ÅK›QXj†\n·g“ócr›¸4ãkr˜ìmrHã&‡œØÑä <·É!)¯\'ÀvDÒfBltm†w¦À¦”¶­ ºìI\"ŠAÅ¤¼h\ZmE~ÊˆxªMAÊëH÷59ŒZÒ!‘òzX’6fó mFl \0`¥~Ë€ÅÅ¢ßn-â¸)TÍù)‡¯Éa¨§ÉÁXkó49Œìlr¨R‚59ôˆ½§¤Í„Ý÷ÐG›á¤°ÏCR€å-\"§umr˜šþµÓÓäp\0ww$\\“CÂ€%i3a˜\rœ}›4aCñdY,O¡ÉÁÌMéÈOÙud_œý©ër^SgGÕä0`IÚLA˜m†w¦H‚+äxÊ~šF{š´˜8lÌû»u^úK\n£ò;@Š¯É!VR^O€MÒfBí{xm†û\nì\nE“Ãjòª¹Éñš°þÔ{æ±©±69ôˆÆX’6)mÆœ `ùâ©[ŸZèkrPóJy\'×åq“Ã£¸“—A* Ñ69$X’6)m†7ïiWè[yøFÄS3½M)Z²-Ìú\"m}Îëhr¸Èà&‡bH2òS‘SÒf\"¥Íˆû–Ã¯ÉM\r¤Íýe=šdüRiQïÀ]ín“C¤Àèîy’6jAsh3©=ä\n½¤¼´ˆ§Ðä`¨E~Š›*¸ÉÁõWËDÑä°w:MÝL¤ã%mÆ· HÐÍƒ|´™žˆ±|ñ79,Öy›°»ÙX‡hrÐ×XãN®…Ä«É!R`t÷<I›‰˜6Ã+Î®ÐÁ+åùšrÐä`Ñ’õâŒ/Wäþ‡_“C)îr,M£ÝGwÆKÚL¤´uzÅMOaåaã8‘¹É!¤¼µhrX‚&{59t,ÑŒ•´™@‚øÜb\'m{Áˆý\n‘ÇJÛn ŒGÌÄÛ§Å${Q0FçŒmHy#Ð9“¢&C¥õê}ï¤Ìt<…¹/;ÒÓ,Ïh€í¹‡í;ÕO›4‰}ŸºE¦Í`÷*—t”Ø¶l>ÜÕ¥¬ñˆÕ+þÇ¾ß}ÿï<^­#b)ÃD\riú©D®ÉÑ“2×ñ‘v þUÜ½½Ë!3 í^áÌz_^>òW‹­ŸMšRå&MÝ\"J>¦:l¾Y,6Âôe—ß»;—ÿ~ö\\,kíÆûú„xcuÿÏñ?æ-ŒÏ9Æ¹ü7ßù½f,®Él1SûEm”ß”AzïÞÁVõ‰f×Õ°¡—èxãMìŠÑF˜…NÊ©H§”j´†‡Z—=#4ä²Û¼åk±¼quÆ3Í¼l\nÍ¸t\ZÕ4UÑôÙSiíåkiÌ¤6\ZÞ\\CóÍ¥•kWÒˆÑ\rÔÔÖHË.[J—.™s«iìä1´fÃjšÞ1ª\Z‡á\'Æ^±“1¶Å;vÆ¶aìXŒ]±Ë0¶c§`ìå«iÆÅÓ©º	cçœ[ÛŠ±‹ñ¹½lì”Y“hÝÆu4¸n ‹µb#L±AeˆÍ°¢WÈ-}™‚Ïá­{UÌH	·Ùxuë0*®q	ËÁÀŠ\\ÝÙéÞ„CÓjŒÔ¼¨†æ\\6“ÊjúÓàáå4mîdšÜ1‘ŠRuË0êX<‹Z\'6SÑ j7‚æ.CUÍ•Ô¯²„Ú§·QÇ¢Y4 º\r®=ŸÇ¥òªÔ4®Š«òáêaA`=DÝ5÷-ŠÍÈƒ….\".ÆñZ,–>4°&ðfãÓ.žBõÓªH$êÃ•a¼Ö\'\"³Ž6ƒ§Í‚6{VŽ›fûcá…Á(e1jHMŠ”ˆÁT˜9š‹´¤Í†{Â±&ÊÄ±˜Q¢$¤w¡|žåï/ªøþÚ|ìáÍûAóªˆ!Wõ	×†‡{Ð˜ïo¹×:Â%zõ³`3fŽ!\nÊ\\Ô0­F¸BÞH:Ø¢ÿážŠXL®0å¼†.š-&ó¬(f‹lr™¯%€—ï=?M|}|ÌŸÇÿ×cƒtVô…4VÏÞVŠw°7„ \'Å¼[}€ÐEx&î¬‚þ½Àúq0`qçñiKr?ldÅ:™+ñnö±‚«;nQŒå§‚Ý±ŸÂØª™`‚EõÄÓé?Óäk¾PÆ2˜|÷ˆuŽA z&`²Ëekr,p…<Óøâ~¸÷í¹VÊæ$C1úæøÂÙ‚Dá“ãå;?7B…‰ÏÅtNéèB!@ârð°óƒ­Éô¤‡€m¡’p?ÍveQíô¡”21\rÜJØ\Z_\0ŸO“\r¨åçÆî]¢Õ³ˆçàÍDó‹\'=4\'°n1\ZÔÖÑBÅíY¤Ã@½Ÿè¶k‹ð‰\nšDM ©g‹)¿oàÉ€˜y\"þÕæXL%W(Î@¸Á$è‹³Ð¤sj¨´\r+ñbF ‡ÚQ\".{,V/.æ:^3 ˆæš«=“–7¾z¸ñmu\\)øëXÍ‰ôEJ²Ö €ç\'·K&Z³é_Â‘cƒ§úŒžáÅ8cÀ¹3f¢\03Ì¸\rûÚ«Ô\0°RœË\nÛ\Z/ÝSB’‰œñ…\n„DþRP©¹kÝ_1ã6ì‹—5$S¡Žôˆú9€OXí)QÉ=éÚbJ¢ê¹ž‹ØŠñÀIi°×ToC\"j6a\ZÉ«Î,\'•O)\"»A?põsˆzY‚k©±æã~èPñnÿÇÀº.¬©ò;á\"®æn-£þ3ó<–+Ä’³§Èã¦¾¬+Qrãf‚ÞlûÇÀïü\Zñ‹»}ÃYq[.åŽ·“æÏ\\Ì(ÎõÐ¤\'œcD†K8(©°Ô”7¶Z1¢üN/›@ei´’cÚÏ1ÍL%ãu1Á\nÈI?eç²šå°ƒálü\nóÂ½1¸xùF{­žì-š³uº ¼ª¾lê{ËÌ+ìÊ<	LÞrÙ†-×mEùÆ°ƒÅZùÆØñËŸÀ9k5º…›QCô¥›H~-5F×Q@Å¼+v¬+á½¼.pAw@å[Ž_>dú©™VË0¥4ý3¸’n®cÈ²Ëk^[›:`Â&H-xéÇ,Þ^8n/ÞRöS~s5,—©R	pùÑW¤[ôT(¢`ôÚz(óÜ«² ¦ÒsZ1•ê¬¥â=âþâÖó£JøX6‹âÃ½YX9[ŒÒ«fÖL1ÂÃÁAºž‰• O2sÁ[dKÅkZôØ‹¶·™Ãz¦ LÄãl¬\0ûd™ÌŸÅSÕ@Ü¨øÙür¬§%,k7J]	þ\ZÏø˜s‡Ú\'ÂE×ÇýlDp¿™¹À¡÷ø+Ÿð¤âðÁ:öÃ<s€/Ö1÷\Z™z_áÚG¼ó%Ö¸]¨sæå3¹>…áKvÓû~ÌŠc=qP§‡àF‰8èJtJ¡„Æ¹ø‰º0\'>Ùõ‰ ÝCÞû„{-úâE^ÿÊ\0ãÒdy¦£\Z˜OÁ?÷qÕð³E3ð1::Øo€\'ƒ™‚0æeOð1Ç)Ì]ç²NŽí1]1X˜x\'Ü?šPD6Ý¨¯ ¼Ñï””W&>u+ä]aÁ8ã‹c7É@“*€Çæ~ˆc€AÈ_†Å¹è°á.hþŸ+Çö°®X×ìîLêËûÛ!ƒ“‚¦\0jÃß˜šÊ„úÿ‚÷^¨ï	ðüô	‰`Ç¾.èHÎõ<]ç¾o×cù¹ÿ¬÷³æ|Ÿþ\0Ù\ráåR{ ]/­ÈûôqIè¯ã¹JJ¯ÑßÅæPñ}êM«@÷f\\Ëk“\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z8O4ðÿ^4ÈHnÍ\0\0\0\0IEND®B`‚'),('10111','JMR','Relucio','Jason','M','Taytay, Rizal	','09118846652','jmr.11@yahoo.com','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0–\0\0\0–\0\0\0<qâ\0\0\0 cHRM\0\0z%\0\0€ƒ\0\0ùÿ\0\0€é\0\0u0\0\0ê`\0\0:˜\0\0o’_ÅF\0\0\0	pHYs\0\0Ã\0\0ÃÇo¨d\0\0\"¤IDATx^í]	x”åµž}ÏÌ$3Ù3Ù$d!û*AdßdUTE©X¥–ªÔª(BAE\\¨U«­·×Þ^µ­­ÞÛúôÚ>×zÕV¯VÔ^esÎ}Ï73aHgÏd&ožç<ÉŸüßÌ?çÿsÎwÎ{¾O¡/©©©©©©©©©©©©©©©©©©©©©©©©©©©©©> 3®q\0¤2	2²²TJÂu°:_™qAT}\0G\n.²²ò<ämÈ	±¨Õj2\Z¤ÑhH­Q“N§#ŽUji´\ZÒéõd0Ä±V«%=Žù§åsñ?96V]A¯*•¸~ò~r\0²RÑÛ@6tä-¾h«ÕJC*+¨|H?Š6\\µžf_:‹\ZF×Ñ‚åóiÓ–«iÜô±ÔÔ>‚V¬[N®^OÍí4fJ]±i-]½„êFÕÒ´9Sèš-›hÖ¥áx8Í[2×;¶]ŽVWk—Ñº+×àÔS=t;óâ4vÂrç‘5Í*XÜ»3W «!iÉY|ø>¶JV›•Fµ¤ÖöfZ¸jÝuß÷iþò¹TÝ<ŒfÌ›J/¢Ò!ÅÔ¯²„Ú¦¢–	”Û/‹Õ–Ó„Ùã¨ªy(å–fSuK%Mî˜@ªúQAY5Ž«§q3ÇRAyž/ÇÆ¨«áe4©c<5´ÕRáÀ|j\Zß@K×-¦©s\'Ñ ºrª=œZÚš(+7ËgÑÞÁ}½Â^(¡/Ž“>u8T×X]BwÝ¿Ve\0’C…ƒ\\”70‹´iJR¤(H›£ ½KEÊT˜â4é\n!yøÝIW¡&>Ûs¬ÂOC¡ŠTžc\rÎÓ(I‰qbl‹®”Ð§±aô«„^M.-åUfQZ‘2KœT?n8ÍY2K³ÝLJîBñ;Hm\"¥Ã‡|Ÿ]ž«ÈEÛo¿•n½ãÛÔ¯¢„²Š3(µ0…Ô\0\0`R¹\0ˆiÃ|±Jü­Ò_lŽ+<ÿ7ÄqŽË=Çüwþ?Ÿ§+ÆïC=Â¿Ë±1êŠïë™õZæy`µ¸?è\\‹{¥ÆžS™NM“ëhàˆþ”æ²‘J+â²¯ k{\\éxó5j\r]ºh>-[¿”\ZÇ4P¿a%dÌÔ’Â‚‹ÃÅ\Zy\0¢/Q¹\ZRïùsƒçØ€/Äà1À­Á1\rÀ²ð1¾¸¡?þWëù¿a°w,ÆÉ±ñÑ•Ð;T~HÍÐ¿	ºf# ÀRk© .›²;ÉVh&µ±3èßÚàÊÂ›¾ÁA^ó˜&Ú~ç-4iÖRš`l°$¥\0\nž_° z?€„×p¯%¸Ìx/þÒrlà1žºbëÅz®ó<Ä|tÞ‡ÜQm¢´2¥c&oí×Mñ—\roöšÙl¦-7m¡%k‘3/™Hä{Ì([(¬[áª¼\0a±5ê´>l¹`†Z®.\n“c“£+#{¾G°b¶áZ²–éÉT¬%•µ3UÁA}\\^‡9¯ÔØ6‚®Ø¼jZ†‘¢‚O6!fâ8Çµz¢b—°\\rlDbÜuÅ¡Âk­š,eZÒaeî×¨î\"ëž,_µŒ.¿v=¥¤›I@ñÍæ8(RëÓõK·¡å’c“¬+W½šÌ1£ÄÄJ©àâtDÌ¹®á|*#7ÖmZC#\'µ.Ý“q\\_§Ù”K×ÖçÃ€N À¥B¦$\rRG\n¥\0×ÎX¬§~›››K×nÝD¥ƒ‹IçDLå\rÒÏ™µuÃúäØ³³ã^B°!I©Wß3ÒIÀÇIHU´àâ(ŸÖN›n¼Š²ûgÉMF°˜ñu‰}¤kóKŸ„H_Öƒ‹ã.ž9z­Ö¢–\'¿UÚ¿”ÆÏG9(½ðì“krÆ\'g¸\0Ë„ô„š+#žúbu¤àšÍÖjåº•´|ýÒØáW,ÞÌÔËÍu$iŒ¸Ïžbœ„ôé0\0àÒ#Á­PpíŽXÏ˜ÌF\Z7c,U·Vzêz(¥tfÊÏcS0û²këÎ÷VË)€uÂU™¯<ü÷Xë˜\Z3u4ijÒs>#L®J&3#O	œºÙ\0®ë¢ÄÞ\rÂ1yÈ×\\\\AÓ.™„J¸’t(Óø‚6™=[U¸Ð]*ƒ‹ƒx¥Q\0ë™pÀº×h2 »^Eeõ¥¢\nÎÈZ@–nñœÊÀ…äÙØ°7S‚|\0P}±—ÿx¥t@	ÈucD-LN/Dhž¦^~ßà1µÿýåÚ°&³svÈLâ€¯Tüõ£QcGÒÂµóÉVj\"\r÷X\nÈqwÁ(7‘ÌR/´±	š¥ú¨NZðë¼q7Ì|õG]ðLnQµLA¶!zÒsÖ•¾ã…Gê&, ÍNC¦s°ÅdÎ´¦vN®Áâ 8àâk4ûu!ŽMV-U$ÉQ‰a—Ò“vØXMŒ¼Ue”S–‰›¥7QÜäQ»E.0L»an\'f™u*˜§°žzf“2Ó”G\n||Ì@Ó2ÉÉal®×]áá3 §+$ê{ä#e2oŸËzWj°˜Yð5^©TÒøí4lL…çFá&uïbW¤nÑg™4Èh¡4\r2»*p³™2Ë×Â9H…Ì÷æB(S ùÿ\Z>7Ç(9©Á‰çsûÔX\\wÈkÆwßß­ë÷Ua¬y˜šRGéD™-Vé=ÌSÿði(13Ô†žŽS€\"Ó6i4œÝ(J8\\tö‘öarÙZ‰\'WÏU^ñÿÿÍq4çv}ïžëÉùÄUÔ`w–Ï) œqVÑWào¹\"\r?ˆÆÂ“ðDÇ›rx1˜Å\Z§Rª(-/•JêòÉZ£N8#”i²Þ\Z7ºÞ	¹Â\\ëóAnPhÛ-šƒ£®˜|töÆùT9¥NM!ÓD­G&y%Ô±÷æiZRÂjRTØžEM21‹·–+š±f¶\\9DŒåI9ü4(°øiª¨D¹™ ô4;D2óŠ1 ÷MëE±‚3vi¸îÉv¡}õïÅî‘m;éà/ž<ö³3ë~»Ž*ïG™Z(}¿	bŽJ2[H˜Òn³Qó‚Z²\r@\'93z}==ºx\'Y\"Æò$Iƒ‹-ÖäÙiø$OƒÂHÀ‘Ùôo”ðûÒþd29ŽÀE~\ré×WôO×íV¤7¹gn¼ç‹Cîï0Íxf2åÞŸJ©÷èÉq¯‘œ÷š¢–ô}èýÃíÊpÑ‚ó(«ÆN\Z<˜	]àv9Å®×Ë*\rm±ª›ª¨uöðÙ•¢ƒ#Xñ9\Z³ÙÕêuëÇq¬Ÿ7ÀâˆÐ­è7û›µwîÿüé¯îzë>j9\\Oé÷š¨b”oK3XEzžZ§4QQk6éyöœÈfÄá\"Æ2D`±2\n”S‘N)Õà9wíìA·èkãÞy,Rhnkíº“7?uðèótão¿E•—QÚ=!Ýõ\0áŠÎLCòÈVfý\"öñWÌ3¾p.•K:ø®%«ÂÅXÜ8QÝ:ŒŠ‡çª+ZpÅìùB1õ¶õmWH\nS¶»zÚ¶¯v½vèãŸÐe/­¢Ò½¹”zwì®¯+X*XŒ4s\Z¾¨…ì¨”píÎ—4®˜ï‘·÷ó†\".FI‡Ûÿ”ú,ÖŒ¹ÓhÄô\ZOÄaP·ˆ\'¡œ¯éÓ‹Î*÷ÄÕw}ùè{þöÍ~nåíNƒëÓÅO²n¾+×™K‹7, œº4Ò0oŽcŸ\0“®¸Ü#_wº¯³­#Ï\nÃ‹¤Åƒ‹¨~j5Yª@œg\Z!¸ºcr}_š•âM7ôM‹EŠÂi§—wß?žþâž?ï¡ÑO4QÆ.K·ã©`ÀÒV¨H­RSEÓ *lÎ\"ÊZ¢Üá¤+šb{À<&·\0žÍcÞXùX>(½,•,CQÒñÖébW¤&W˜oX+ž‰öI`	’®­zÕ©­|ò,Ýüú-Tu`9î1Æ%ž\n,5njŠ>…ú×–¹90Xf…FÃ›‹ôuMcð=Š`±‘ð1€ÕØÞ@åÍ¥b€¸ÐDÑføs`V½­E}Çb¹ÆTwÿ	7žøÁËò­ÿåzêÿÃ|ë°º¤/b,ÜØ[Mž?žå)¤å4N\"{<6aÓ\rl±¦vL¡–Ù\rÂ¤ÆZØŒÉ-ò²FœnàeúJMq•Ô¾bç—Þyäƒ#tñó³ÉµÛ‰ =¾ñT0‹¥\Z¨¤t{:]ºf¹\Z¤e×ÔšPa`½°ëe²_$1VFa:\ri+§”\Zt¾zó\">NV¬fÓ·’I0—ÊSdf„ì¯½Ä±Þo±H‘?á›Å7ïù¿§>¿ÿíýÔþäHÊÜ•â§zÎRùç±8ÆÒ¨4TT™O¹õN2òÈ·¨G–«;õ_A›á	âp±êP¨Ì;§ú+¥Œ²4\\ \'ÆJm†ý5O]yµ>oçGïÇSnEÅÒS›|äèO¾ÙþŸ;hø#CÄŒ/ù©H]\'»BŽ±¬+UŽL¦B-y1Ž}¢è0u¶ÈŸã£Í¨Â‹K:ÜS9f i™•@ÚŒ:¹}Û³\\Nï–[¡7»KÇ^wbçK‡>yž6¾¼‘Ê÷\n+•vo÷“ž‘‚Ê— å’NŽ#—f¯˜ICl¢¤ã[ë*\ZpÅºp‰Ži3°Zác,¦ÍLmfN£‡L—hÚÇXž^µÞ,,1Wæ¹pÇñý:ø¿ÏÐ¥?½„\nÀò˜qÎOE\n.ÅRQŠÉJÓL¡Â–LÒrz(‘Œ_¦Í°+Œ¤¤“4ÚÂÉ¶^YÒq+rÚÜó·ì>öÔ§{Þ9@ŽŒ¥¬û¬\"“)â}³!4¨ç¢V˜7(›Ò‡‚~’ˆÐEPÄc¡Íä%Ÿ6Ó;,Ó\rÝŠN_½çáOŸ9µãwRýcUä¼ÇÔ#IÏˆÁ·µÂ=H7àA´mÔ0q8YŠ@›á>€&ïR›½‘6S#i3ÌóÔÜy­WÜñÂá£/Ð¦W¯¡A– @G<‡\"rÄ êJ©¨œwØÛŒ¤@ÁÞ•ÚÌ:ÐfªA›ANIÒfp²z\rmÆ­H)v7Ì½íø¾?üèYZôâB*Ü“•üT8À9všÈ¾É@ÆvòGp…Ø\ZFÒfBÐ9z\rmv`¤»cÓ®c‡?Ú÷îc4åéñ”}¿½[¤¼p`	û¶R°\\ŽïÉ¶.¯´d³Zpç-V/m¦\\Òf›ëdÓf<¤¼sN¯¿ûÁÏž=yçï¦¦CÃAÊK~<å¼Kco7RÊ=iË5èŠQ‘eœãku‘öë4[ª¤Í„¤s$“6Chsp§Øxò¶g\"?uýo¶PÅCýzG<õ\0û&Yæè°›Ö€5iÈÚ‘qÜ¹Ùõk…^ùAnŽ¤Í„¥9\'…6ƒ=5òÝÕ³nùz÷ë‡>~Ž–ÿ|•ìÍñ¸¾,\"‡uìú8žº.nÖY·\"“Ž‰ÃšœÏìË²^Q:Ô7Ã¾Î[òIÚŒÿV\'~Ú¤ÑfÜŠŒ:÷Ô\rw;øáCï?Ž&‡)ž&‡82=#P s·#žÚ€xª®ƒtìá¸:ïCËtÇÏPTú@Å›Y>ÏÛÂIÚL(}¢i3nEÉ¬ÓkwîûÌÓäÐz¸2âÐä+Ä8Ò‘#Kûâ©åØ´ Ò¨ÈÔh?ã¼ÎõCSÊS\0ÓFˆoÝÏ#™9™’6’+”(ÚŒhrH«[{jÛ¡Éaëï¶ÑP498âÔä3°TOmC<5ñT¾\Z¥5Ygf|åØ”û¦n€þA\0j	¤¢÷¶˜ÉÈÌ´™¤ÓfÐäàpœºíë]¯þûó´ú—Qéó<®/ÉIOÇ÷O]‡xj’ŽTiˆ§œzJ]‘ýÔUY¿V;µ?\0.‚ð>Îj¿¾Å#\Z½¤Í¥s$„6ãV8jÎL^ƒ&‡wüíIO“ÃýñorˆÚbÁR9n7‘í\nZŸ2¨Ð!e&ÇU®R:œ?G|õm\0iÄ	áÅñü_Gl›¤Í„¤9÷$mMS¾Y¶}ïçG¾Üõç½ÔöD³§É!‰EäÎxê6#YW\"žªÄ¾Ì\Z%Ö_ñÔÿ˜Z­OAWCx‡Sk@ùäåçIÚL8&ª(éÄ“6#Hy¦ªU\'¶|ä“çèæ7n¥ênrˆÈbq<…¤gêÍF²\\¢\nñ”^M)Sœ_;6åýQ7Èø0³2j_æ#¼·¤Í„ª¸Ç›6ƒ&§»|Â\r\'îúw&åq“Ã€}ù\"é™ôüÇS[OMÓáAB<eÓ’}iö±ÔµÙ¯©3µ÷Lˆ&ˆ¥ê´X³AÒf‚1¬6Ó=Úš¹G/½óøÃo?úÁÓ4÷ùÊßž4Rž¿sÜxêJ=G!ž2a¹ó\"ÄXWä}l›þ’Ò¨ÜÄ`2±ÒN×x*ÆŽØvI›	¹Àr\\qYmÆ­pMâ&‡c?ò69Œ¢,nrñTÏ79u…œŸú.â©Ë°¶k•§Þg¨¶}ã¸ÎõžyŒ×I¿Ò\0±…±Rçïù…ù’6lKß¸¬6#HyÚÊ%§7ïø“g¿ùÎï¿GµV\n0±û‹(ö‰a9¡°ïë‹§¾¤ç‘KOqy’ó„óZ×[úJÓc@Ê*È`oÍëˆÞ i3!WAémMzw~Ûæw¼ô8Hy_¾’Ê÷{›’œŸ¤¼džx*Ed«Žl²¿H]Ÿó;užî> ˆw)‚h£A”÷Ü#›Å³ÚŒ¤Ía9ÆJ›A“Ã@49|ïø¾?=úái¯É¡7ÔûîD~5HycPD¶`µ\\XÏ\ryGm3þMiQ~à˜É‚¨b\09âÈpHÚLÜi3nEîè3s¯¿ÿØG÷¾óMM¶¤óÑ)o‡‰¬kOa\'x…¬µº›óß7·ÿ0º àåÏí1ªsVÈy,¹ÚLUP¢^mÆÛä°ðÔUøô¹S;ÞÜI\rÜä€xª»+å…™BÅa¾xêVÄS‹O\ràx\nØM;éØìú³±ÆrˆX©„˜º	*a±$m&³!*ÚŒ_“¯”wÍ¯®¥Áû¹É!>+åuXOm5yâ©l/)o^Æ—iWæ¾¡)ÐíæCJbŒ§áðˆ¤Í„[K Úš\nÜuo?þÀ›‡>zŽ–üË\"*â&‡$5v‚ë}LÊ»IÏvÄSVÄSÙ\0úºÜOm‹3_VZÕw\0S 9Ýˆ§KÒfÂ­dŽ6ãVd¶œéØtß±Ç?æ&‡©OO œûÐä€•]‚Z±®M\rHz2)o`ŠÛyMÞ–)i/ wÎkÕ·BÒâàúº¾…¤Í„Z©&äj3MîÞÿÙÓ\'Î69˜“ŸŸâ¤\'79,E<5ÐKÊ™vÊy½ë¿\rõ)O\0¼q3=Í=\0*OŒ%i3ÁWA	B›áXD¡p§6l8yË!?uýon !õïUMæ¿&‡9Çó~¯)1üW¾Ò¢ë!P	`IÚŒÿÂ¨Üâ¹´™ãŠŠ¡…îæq·|µû\rnrXñ¯ËÑäÜþ>ßŒ‹È›¹É¤<;“òO­ÊùÜ¾2ëU49ìÄ\rŸáýµýIy=/I›‰dó ¿ýŽ\ry±õ¶ûN?ùnr˜ùÌTïN=¿R^ØxŠIy—ƒ”×ˆüâ)Ý\049lr}h™áxùª›€žQGO (À{JÚLØ¾¼´øXjµæôm¿Úuj×»{Ñä0M£=³òpÄ©¿&«¯Éõ>S“ý´ãZ×;¦&«¯É¡7ß’ P	W(i3!v:ð§Í(°­œAo¢‡WRÝC{Q“HyóO0)OC)3ÒAÊs¡ÉÁÈMK!þM‰Â–¤Í„]ÅK›QXj†\n·g“ócr›¸4ãkr˜ìmrHã&‡œØÑä <·É!)¯\'ÀvDÒfBltm†w¦À¦”¶­ ºìI\"ŠAÅ¤¼h\ZmE~ÊˆxªMAÊëH÷59ŒZÒ!‘òzX’6fó mFl \0`¥~Ë€ÅÅ¢ßn-â¸)TÍù)‡¯Éa¨§ÉÁXkó49Œìlr¨R‚59ôˆ½§¤Í„Ý÷ÐG›á¤°ÏCR€å-\"§umr˜šþµÓÓäp\0ww$\\“CÂ€%i3a˜\rœ}›4aCñdY,O¡ÉÁÌMéÈOÙud_œý©ër^SgGÕä0`IÚLA˜m†w¦H‚+äxÊ~šF{š´˜8lÌû»u^úK\n£ò;@Š¯É!VR^O€MÒfBí{xm†û\nì\nE“Ãjòª¹Éñš°þÔ{æ±©±69ôˆÆX’6)mÆœ `ùâ©[ŸZèkrPóJy\'×åq“Ã£¸“—A* Ñ69$X’6)m†7ïiWè[yøFÄS3½M)Z²-Ìú\"m}Îëhr¸Èà&‡bH2òS‘SÒf\"¥Íˆû–Ã¯ÉM\r¤Íýe=šdüRiQïÀ]ín“C¤Àèîy’6jAsh3©=ä\n½¤¼´ˆ§Ðä`¨E~Š›*¸ÉÁõWËDÑä°w:MÝL¤ã%mÆ· HÐÍƒ|´™žˆ±|ñ79,Öy›°»ÙX‡hrÐ×XãN®…Ä«É!R`t÷<I›‰˜6Ã+Î®ÐÁ+åùšrÐä`Ñ’õâŒ/Wäþ‡_“C)îr,M£ÝGwÆKÚL¤´uzÅMOaåaã8‘¹É!¤¼µhrX‚&{59t,ÑŒ•´™@‚øÜb\'m{Áˆý\n‘ÇJÛn ŒGÌÄÛ§Å${Q0FçŒmHy#Ð9“¢&C¥õê}ï¤Ìt<…¹/;ÒÓ,Ïh€í¹‡í;ÕO›4‰}ŸºE¦Í`÷*—t”Ø¶l>ÜÕ¥¬ñˆÕ+þÇ¾ß}ÿï<^­#b)ÃD\riú©D®ÉÑ“2×ñ‘v þUÜ½½Ë!3 í^áÌz_^>òW‹­ŸMšRå&MÝ\"J>¦:l¾Y,6Âôe—ß»;—ÿ~ö\\,kíÆûú„xcuÿÏñ?æ-ŒÏ9Æ¹ü7ßù½f,®Él1SûEm”ß”AzïÞÁVõ‰f×Õ°¡—èxãMìŠÑF˜…NÊ©H§”j´†‡Z—=#4ä²Û¼åk±¼quÆ3Í¼l\nÍ¸t\ZÕ4UÑôÙSiíåkiÌ¤6\ZÞ\\CóÍ¥•kWÒˆÑ\rÔÔÖHË.[J—.™s«iìä1´fÃjšÞ1ª\Z‡á\'Æ^±“1¶Å;vÆ¶aìXŒ]±Ë0¶c§`ìå«iÆÅÓ©º	cçœ[ÛŠ±‹ñ¹½lì”Y“hÝÆu4¸n ‹µb#L±AeˆÍ°¢WÈ-}™‚Ïá­{UÌH	·Ùxuë0*®q	ËÁÀŠ\\ÝÙéÞ„CÓjŒÔ¼¨†æ\\6“ÊjúÓàáå4mîdšÜ1‘ŠRuË0êX<‹Z\'6SÑ j7‚æ.CUÍ•Ô¯²„Ú§·QÇ¢Y4 º\r®=ŸÇ¥òªÔ4®Š«òáêaA`=DÝ5÷-ŠÍÈƒ….\".ÆñZ,–>4°&ðfãÓ.žBõÓªH$êÃ•a¼Ö\'\"³Ž6ƒ§Í‚6{VŽ›fûcá…Á(e1jHMŠ”ˆÁT˜9š‹´¤Í†{Â±&ÊÄ±˜Q¢$¤w¡|žåï/ªøþÚ|ìáÍûAóªˆ!Wõ	×†‡{Ð˜ïo¹×:Â%zõ³`3fŽ!\nÊ\\Ô0­F¸BÞH:Ø¢ÿážŠXL®0å¼†.š-&ó¬(f‹lr™¯%€—ï=?M|}|ÌŸÇÿ×cƒtVô…4VÏÞVŠw°7„ \'Å¼[}€ÐEx&î¬‚þ½Àúq0`qçñiKr?ldÅ:™+ñnö±‚«;nQŒå§‚Ý±ŸÂØª™`‚EõÄÓé?Óäk¾PÆ2˜|÷ˆuŽA z&`²Ëekr,p…<Óøâ~¸÷í¹VÊæ$C1úæøÂÙ‚Dá“ãå;?7B…‰ÏÅtNéèB!@ârð°óƒ­Éô¤‡€m¡’p?ÍveQíô¡”21\rÜJØ\Z_\0ŸO“\r¨åçÆî]¢Õ³ˆçàÍDó‹\'=4\'°n1\ZÔÖÑBÅíY¤Ã@½Ÿè¶k‹ð‰\nšDM ©g‹)¿oàÉ€˜y\"þÕæXL%W(Î@¸Á$è‹³Ð¤sj¨´\r+ñbF ‡ÚQ\".{,V/.æ:^3 ˆæš«=“–7¾z¸ñmu\\)øëXÍ‰ôEJ²Ö €ç\'·K&Z³é_Â‘cƒ§úŒžáÅ8cÀ¹3f¢\03Ì¸\rûÚ«Ô\0°RœË\nÛ\Z/ÝSB’‰œñ…\n„DþRP©¹kÝ_1ã6ì‹—5$S¡Žôˆú9€OXí)QÉ=éÚbJ¢ê¹ž‹ØŠñÀIi°×ToC\"j6a\ZÉ«Î,\'•O)\"»A?põsˆzY‚k©±æã~èPñnÿÇÀº.¬©ò;á\"®æn-£þ3ó<–+Ä’³§Èã¦¾¬+Qrãf‚ÞlûÇÀïü\Zñ‹»}ÃYq[.åŽ·“æÏ\\Ì(ÎõÐ¤\'œcD†K8(©°Ô”7¶Z1¢üN/›@ei´’cÚÏ1ÍL%ãu1Á\nÈI?eç²šå°ƒálü\nóÂ½1¸xùF{­žì-š³uº ¼ª¾lê{ËÌ+ìÊ<	LÞrÙ†-×mEùÆ°ƒÅZùÆØñËŸÀ9k5º…›QCô¥›H~-5F×Q@Å¼+v¬+á½¼.pAw@å[Ž_>dú©™VË0¥4ý3¸’n®cÈ²Ëk^[›:`Â&H-xéÇ,Þ^8n/ÞRöS~s5,—©R	pùÑW¤[ôT(¢`ôÚz(óÜ«² ¦ÒsZ1•ê¬¥â=âþâÖó£JøX6‹âÃ½YX9[ŒÒ«fÖL1ÂÃÁAºž‰• O2sÁ[dKÅkZôØ‹¶·™Ãz¦ LÄãl¬\0ûd™ÌŸÅSÕ@Ü¨øÙür¬§%,k7J]	þ\ZÏø˜s‡Ú\'ÂE×ÇýlDp¿™¹À¡÷ø+Ÿð¤âðÁ:öÃ<s€/Ö1÷\Z™z_áÚG¼ó%Ö¸]¨sæå3¹>…áKvÓû~ÌŠc=qP§‡àF‰8èJtJ¡„Æ¹ø‰º0\'>Ùõ‰ ÝCÞû„{-úâE^ÿÊ\0ãÒdy¦£\Z˜OÁ?÷qÕð³E3ð1::Øo€\'ƒ™‚0æeOð1Ç)Ì]ç²NŽí1]1X˜x\'Ü?šPD6Ý¨¯ ¼Ñï””W&>u+ä]aÁ8ã‹c7É@“*€Çæ~ˆc€AÈ_†Å¹è°á.hþŸ+Çö°®X×ìîLêËûÛ!ƒ“‚¦\0jÃß˜šÊ„úÿ‚÷^¨ï	ðüô	‰`Ç¾.èHÎõ<]ç¾o×cù¹ÿ¬÷³æ|Ÿþ\0Ù\ráåR{ ]/­ÈûôqIè¯ã¹JJ¯ÑßÅæPñ}êM«@÷f\\Ëk“\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z\Z8O4ðÿ^4ÈHnÍ\0\0\0\0IEND®B`‚'),('2232','sss','ñss','sds','dd','qqwe','123123','wewq',NULL);
/*!40000 ALTER TABLE `facultystaff` ENABLE KEYS */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `LOCATIONS` varchar(30) COLLATE utf8_bin NOT NULL,
  UNIQUE KEY `Locations_UNIQUE` (`LOCATIONS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('Computer Laboratory'),('Library'),('Registrar'),('Room 201'),('Room 202'),('Room 203'),('Room 204'),('Room 205'),('Technical Laboratory');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-20 14:04:14
