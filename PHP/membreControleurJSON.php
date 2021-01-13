<?php
 require_once("../BD/connexion.php");
 $tab=array();
 function enregistrer(){
	global $tab,$connexion;
	$rep="../photos/";
	try{
		 //$image = $_POST['image'];
		 $titre=$_POST['titre'];
		 $auteur=$_POST['auteur'];
		 $annee=$_POST['annee'];
		 $pages=$_POST['pages'];
		 $requete="INSERT INTO livres VALUES(0,?,?,?,?)";
		 $stmt = $connexion->prepare($requete);
		 $stmt->bind_param("ssii", $titre,$auteur,$annee,$pages);
		 $stmt->execute();
		 $tab[0]="OK";
	 }catch (Exception $e){
		 $tab[0]="NOK";
	 }finally {
		echo json_encode($tab);
	 }
 }
 function lister(){
	 global $tab,$connexion;
	 $requete="SELECT * FROM livres";
	 try{
		 $listeLivres=mysqli_query($connexion,$requete);
		 $tab[0]="OK";
		 $i=1;
		 while($ligne=mysqli_fetch_object($listeLivres)){
				$tab[$i]=array();
				$tab[$i]['idlivre']=$ligne->idlivre;
				$tab[$i]['titre']=$ligne->titre;
				$tab[$i]['auteur']=$ligne->auteur;
				$tab[$i]['annee']=$ligne->annee;
				$tab[$i]['pages']=$ligne->pages;
				//$tab[$i]['image']=base64_encode(file_get_contents("../photos/30.jpg"));
				$i++;
		 }
	 }catch (Exception $e){
		 $tab[0]="NOK";
	 }finally {
		echo json_encode($tab);
	 }
 }
 
 function modifier(){
	global $tab,$connexion;
	$idlivre=$_POST['idlivre'];
	$requete="SELECT * FROM livres WHERE idlivre=?";
	try{
		 $stmt = $connexion->prepare($requete);
		 $stmt->bind_param("i", $idlivre);
		 $stmt->execute();
		 $result = $stmt->get_result();
		 $tab[0]="OK";
		 $i=1;
		 if($ligne = mysqli_fetch_object($result)){
			$tab[$i]=array();
			$tab[$i]['idlivre']=$ligne->idlivre;
			$tab[$i]['titre']=$ligne->titre;
			$tab[$i]['auteur']=$ligne->auteur;
			$tab[$i]['annee']=$ligne->annee;
			$tab[$i]['pages']=$ligne->pages;
		}else{
			$tab[0]="Ce livre est inexistant !!!";
		}
	}catch (Exception $e){
		 $tab[0]="NOK";
	}finally {
		echo json_encode($tab);
	 }	
 }
 function maj(){
	global $tab,$connexion;
	try{
		 $idlivre=$_POST['idlivre'];
		 $titre=$_POST['titre'];
		 $auteur=$_POST['auteur'];
		 $annee=$_POST['annee'];
		 $pages=$_POST['pages'];
		 $requete="UPDATE livres SET titre=?,auteur=?,annee=?,pages=? WHERE idlivre=?";
		 $stmt = $connexion->prepare($requete);
		 $stmt->bind_param("ssiii",$titre,$auteur,$annee,$pages,$idlivre);
		 $stmt->execute();
		 $tab[0]="OK";
	 }catch (Exception $e){
		 $tab[0]="NOK";
	 }finally {
		echo json_encode($tab);
	 }
 }
 function enlever(){
	 global $tab,$connexion;
	try{
		 $idlivre=$_POST['idlivre'];
		 $requete="DELETE FROM livres WHERE idlivre=?";
		 $stmt = $connexion->prepare($requete);
		 $stmt->bind_param("i", $idlivre);
		 $stmt->execute();
		 $tab[0]="OK";
	 }catch (Exception $e){
		 $tab[0]="NOK";
	 }finally {
		echo json_encode($tab);
	 }
 }
 
 //Le controleur
 $action=$_POST['action'];
 switch($action){
	case "enregistrer":
	   enregistrer();
	   break;
	case "lister":
		lister();
		break;
	case "modifier":
		modifier();
		break;
	case "maj":
		maj();
		break;
	case "enlever":
		enlever();
		break;
 }
 mysqli_close($connexion);
?>
