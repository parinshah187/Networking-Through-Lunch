<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start page</title>

<style type="text/css">

.topshadow {
  height: 140px;
  width: 140%;
  margin: -62px;
  background-image:url('http://www.psdgraphics.com/file/red-stage.jpg'); 
  position: absolute; 
  },gn
.middleshadow {
  top:95px;
  height: 6px;
  width: 140%;
  margin: -10px;
  background-image:url('http://crownedwithhisglory.weebly.com/uploads/1/6/9/6/16963932/8769914.png'); 
  position: absolute; 
  }  
  
body {
background:url('http://img714.imageshack.us/img714/6058/gray16.png');
}

h1 {

  color:#F1F8E0;
  font-size: 25px; 
  position: relative; 
}

.ribbon {
   position: relative;
   top: -16px;
   left: -600px;
}

table {
    float:left;
    margin-left:220px;
  }
  
td
{
border:2px solid black;
background-color:#610B0B;
color:#F1F8E0;


}

.td1 {
background-color:#151515;
}
.table1postion {
align ="left"
}

.table2postion {
align ="right"
}

.img1 {
margin-left:0px;
top:102px;

}

.img2 {
margin-left:0px;
top:102px;
}

.img3 {
margin-left:0px;
top:102px;
}

input[type="file"]
    { 
        
      background: grey;
        
               
    } 

.button {
   border-top: 1px solid #B40404;
   background: #FA8258;
   
  }
  
.button:hover {
   border-top-color: #28597a;
   background: #28597a;
   color: #ccc;
   }
   
 </style>
 
 <script type= "text/javascript">
 
 
 </script>
 
</head>

<body>
<div class="topshadow">
</div>



<div class="middleshadow">
</div>
<!--  
 <div> 
<img src='http://iam.uic.edu/wp-content/uploads/2013/02/networking.jpg'  width="40" height="22" class="ribbon"/>
</div> 
-->
<br/>
<h1 style ="font-family:Lucida Console" > &nbsp; &nbsp; Networking at work through lunch </h1>


<div> 
<img class="img1" src='http://www.painetworks.com/photos/jb/jb3881.JPG' width="434" height="280"/> <img class="img2" src='http://redcrossselanprc.files.wordpress.com/2010/06/arc-lunch.jpg' width="434" height="280" /> <img class="img3" src='http://ie-main.cdn.grabone.com/goimage/440x267/i3lee.jpg' width="434" height="280"  />
</div>

<br/>
<br/>
<br/>
<br/>
<br/>


<form  id  =  "form1"   method =  "post"  action  = "/NetworkingAtWorkThroughLunch/StartServlet" >

<table class="table1postion" align="right" >
<tr>
<td > &nbsp;&nbsp; <b>Click on Start to generate today's list </b> &nbsp;&nbsp;</td>
</tr>

<tr>
<td class="td1" align="center" ><b><input type="submit" value="     Start     " class="button" ></b> </td>
</tr>
</table>

</form>



<form action="/NetworkingAtWorkThroughLunch/FileUploadServlet" method="post" enctype="multipart/form-data">
<table class="table2postion" align="center">
<tr>
<td><input type="file" name="file" size="20" /></td>
</tr>
<tr>
<td class="td1" colspan="2" align="center"><b><INPUT TYPE="submit" value="     Upload     " class="button"></b></td>
</tr>
</table>

</form>

</body>
</html>