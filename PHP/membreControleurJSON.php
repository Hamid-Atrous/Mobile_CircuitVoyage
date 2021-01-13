<?php
 require_once("../BD/connexion.php");


 $tab=array();


 function lister(){
	 global $tab,$connexion;
	 
	 $requete="SELECT * FROM membre";
	 try{
		 $listMembres=mysqli_query($connexion,$requete);
		 $tab[0]="OK";
		 $i=1;
		 while($ligne=mysqli_fetch_object($listMembres))
		 {
				$tab[$i]=array();
				$tab[$i]['idMembre']=$ligne->idMembre;
				$tab[$i]['nom']=$ligne->nom;
				$tab[$i]['prenom']=$ligne->prenom;
				$tab[$i]['courriel']=$ligne->courriel;
				$tab[$i]['telephone']=$ligne->telephone;
				$i++;
		 }
	 }catch (Exception $e){
		 $tab[0]="NOK";
	 }finally {
		echo json_encode($tab);
	 }
 }
 



 
 //Le controleur
 $action=$_POST['action'];

 switch($action)
 {
	case "lister":
		lister();
		break;
 }
 mysqli_close($connexion);

?>
