if(!settings.multipleView) settings.batchView=false;
settings.tex="pdflatex";
settings.inlinetex=true;
deletepreamble();
defaultfilename="SDD-1";
if(settings.render < 0) settings.render=4;
settings.outformat="";
settings.inlineimage=true;
settings.embed=true;
settings.toolbar=false;
viewportmargin=(2,2);

import graph;
size(10cm,0);

// Nodos
pair login = (0,0);
pair home = (4,0);
pair station = (2,-2);
pair profile = (6,-2);
pair lang = (6,-4);

// Dibuja nodos
draw(circle(login,0.5));
label("Iniciar sesión", login);
draw(circle(home,0.8));
label("Pantalla Principal", home);
draw(circle(station,0.7));
label("Estacion", station);
draw(circle(profile,0.7));
label("Perfil", profile);
draw(circle(lang,0.6));
label("Idioma", lang);

// Flechas
draw(arrow(login--home));
draw(arrow(home--station));
draw(arrow(home--profile));
draw(arrow(profile--lang));
