<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style type="text/css">
/*--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
--*/
/* start editing from here */
html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,dl,dt,dd,ol,nav ul,nav li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline;}
article, aside, details, figcaption, figure,footer, header, hgroup, menu, nav, section {display: block;}
ol,ul{list-style:none;margin:0;padding:0;}
blockquote,q{quotes:none;}
blockquote:before,blockquote:after,q:before,q:after{content:'';content:none;}
table{border-collapse:collapse;border-spacing:0;}
/* start editing from here */
a{text-decoration:none;}
.txt-rt{text-align:right;}/* text align right */
.txt-lt{text-align:left;}/* text align left */
.txt-center{text-align:center;}/* text align center */
.float-rt{float:right;}/* float right */
.float-lt{float:left;}/* float left */
.clear{clear:both;}/* clear float */
.pos-relative{position:relative;}/* Position Relative */
.pos-absolute{position:absolute;}/* Position Absolute */
.vertical-base{	vertical-align:baseline;}/* vertical align baseline */
.vertical-top{	vertical-align:top;}/* vertical align top */
.underline{	padding-bottom:5px;	border-bottom: 1px solid #eee; margin:0 0 20px 0;}/* Add 5px bottom padding and a underline */
nav.vertical ul li{	display:block;}/* vertical menu */
nav.horizontal ul li{	display: inline-block;}/* horizontal menu */
img{max-width:100%;}
/*end reset*/
/*--login form start here--*/ 
body {
/*    background:url(../images/sun.jpg)no-repeat; */
   background-size: cover;
   font-family: 'Open Sans', sans-serif;
   font-size: 100%;
}
.not-found {
    margin: 5em auto 0em;
    padding: 7em 3em 7em 3em;
    background:#fff;
    border-radius: 5px;
    width: 55%;
}
.notfound-top h1 {
    font-size: 9.5em;
    color: #94a531;
    padding-top: 0.2em;
}
.notfound-top {
  float: left;
  width: 35%;
  text-align: right;
  padding: 0em 4em 0em 0em;
}
.content {
  float: right;
  width:50%;
  padding-left: 3em;
  border-left: 2px solid #94a531;
}
.content h3 {
  font-size: 14px;
  font-weight: 500;
  line-height: 1.8em;
  color:#656262;
  margin: 20px 0px;
}
.content input[type="text"] {
  outline: none;
  font-size: 0.8em;
  font-weight: 400;
  color: #ccc;
  padding: 7px 9px;
  width: 40%;
  border: 1px solid #B5B4B1;
}
.content input[type="text"]:hover {
    border: 1px solid #94a531;
    box-shadow: 0px 0px 2px 0px rgba(148, 165, 49, 0.78);
    transition: 0.5s all;
    -webkit-transition: 0.5s all;
    -moz-transition: 0.5s all;
    -o-transition: 0.5s all;
}
.content input[type="submit"] {
  outline: none;
  font-size: 0.9em;
  font-weight: 500;
  color: #fff;
  padding: 8px 9px;
  width: 15%;
  border:none;
  cursor:pointer;
  background:#94a531;
}
.content input[type="submit"]:hover {
  background: #636161;
  color:#fff;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
}
ul.social-icon {
  margin: 1em 0em 0em 1.5em;
}
ul.social-icon li {
  list-style: none;
  display: inline-block;
}
ul.social-icon li a {
  background: url(../images/t-icn.png)no-repeat;
  width: 25px;
  height: 25px;
  display: inline-block;
  cursor:pointer;
  margin: 0em 0.5em 0em 0em;
}
ul.social-icon li a.fa {
  background-position: 0px 0px;
}
ul.social-icon li a.tw {
  background-position: -27px 0px;
}
ul.social-icon li a.g {
  background-position: -53px 0px;
}
ul.social-icon li a.fa:hover {
  background-position: 0px 0px;
  opacity: 0.8;
}
ul.social-icon li a.tw:hover {
  background-position: -27px 0px;
  opacity: 0.8;
}
ul.social-icon li a.g:hover {
  background-position: -53px 0px;
  opacity: 0.8;
}
.copyright {
  padding: 5em 0em 3em 0em;
  text-align: center;
}
.copyright p {
  font-size: 15px;
  font-weight: 400;
  color: #fff;
  line-height: 1.7em;
  /*-- w3layouts --*/
}
.copyright p a{
  font-size: 15px;
  font-weight: 400;
  color: #fff;
}
.copyright p a:hover{
	color:#94a531;
	 transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
}
/*--meadia quiries start here--*/
@media(max-width:1440px){
.copyright {
  padding: 3em 0em 3em 0em;
}
}
@media(max-width:1366px){
.not-found {
    width: 60%;
}	
}
@media(max-width:1280px){
.not-found {
    width: 62%;
}
}
@media(max-width:1024px){
.notfound-top {
    padding: 0em 0em 0em 0em;
}
.content input[type="submit"] {
    width: 20%;
}
.copyright {
    padding: 3em 1em 3em 1em;
}
.content h3 {
    margin: 10px 0px;
}
.notfound-top h1 {
    font-size: 8em;
    padding-top: 0.35em;
}
}
@media(max-width:768px){
.notfound-top h1 {
/*-- agileits --*/
    font-size: 7.5em;
}
.content {
    width: 52%;
    padding-left: 2em;
}
.not-found {
    width: 78%;
    padding: 5em 3em 5em 3em;
    margin: 12em auto 0em;
}
.content h3 {
    font-size: 13px;
}
.copyright {
    padding: 13em 0em 5em 0em;
}
}
@media(max-width:667px){
.notfound-top h1 {
    font-size: 6.5em;
    padding-top: 0.5em;
}	
}
@media(max-width:640px){
.notfound-top {
    float: none;
    width: 34%;
    margin: 0 auto;
}
.content {
    width: 100%;
    float: none;
    margin: 0 auto;
    padding-left: 0em;
    text-align: center;
    border-left: none;
}
.notfound-top h1 {
    padding-top: 0em;
}
.not-found {
    width: 78%;
    padding: 3em 3em 3em 3em;
    margin: 7em auto 0em;
}
.copyright {
    padding: 4em 0em 3em 0em;
}
/*-- w3layouts --*/
ul.social-icon {
 margin: 1em 0em 0em 0em;
}
}
@media(max-width:600px){
.notfound-top {
    width: 39%;
}
}
@media(max-width:568px){
.notfound-top {
    width: 42%;
}
}
@media(max-width:480px){
.not-found {
    width: 70%;
     margin: 6em auto 0em;
}
.notfound-top h1 {
    font-size: 5em;
}
.content img {
    width: 20%;
}
.copyright p {
    font-size: 13px;
}
.copyright {
    padding:4em 0.4em 2em 0.4em;
}
.notfound-top {
    width: 41%;
}
}
@media(max-width:414px){
.notfound-top {
    width: 50%;
}
.copyright {
    padding: 5.5em 0.4em 4em 0.4em;
}
}
@media(max-width:384px){
.notfound-top {
    width: 53%;
}
.not-found {
    padding: 2em 3em 2em 3em;
/*-- agileits --*/ 
}

.copyright {
    padding: 3.5em 0.4em 3em 0.4em;
}
}
@media(max-width:320px){
.not-found {
    width: 83%;
    margin: 3em auto 0em;
    padding: 1em 1em 1em 1em;
}
.notfound-top {
    width: 44%;
}
.notfound-top h1 {
    font-size: 4em;
}
.content input[type="submit"] {
    width: 28%;
}
.content h3 {
    margin: 10px 0px;
    font-size: 12px;
}
.content input[type="text"] {
    width: 55%;
}
.copyright p a {
    font-size: 13px;
}
}
</style>