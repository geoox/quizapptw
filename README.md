# quizapptw
3 decembrie

Doinita:
	
* Am pus un buton in activitatea de login (main activity) ce permite sa dai testul anonim (adica daca apesi pe butonul deschide activitatea cu testul anonim). Am facut asta adaugand metoda JoinTest in LoginActivity.java .

* Am modificat fisierul Manifest, adaugand la activitatea Join Test Activity si cine e activitatea parinte. (PRO TIP:se poate seta asta cand creezi o activitate noua, iti da posibilitatea si sa alegi who's his daddy. ). Asta ajuta ca atunci cand apesi pe butonul inapoi sa te duca la activitatea parinte.

* Am creat o noua activitate Student Main (de folosit in locul celei vechi Student Main Activity) pentru a testa cum merge layout-ul cu drawer. Momentan activitatea se poate accesa apasand pe butonul de login (ca alta idee nu mi-a venit ca sa o pot testa rapid). Layout-ul a creat 4 xml-uri in total pentru activitatea Student Main (nav_header_student_main, content_student_main, app_bar_student_main si activity_student_main2 care nu stiu ce rost mai are daca exista celelalte 3).

* Nu am sters StudentMainActivity in cazul in cae ne hotaram sa il folosim pe ala

4 decembrie

Andreea:

* Am creat activity_generate_code pe care am legat-o la butonul Get Code din activitatea de configurat teste pentru profesori

* Am creat activity_test_options catre care nu putem naviga momentan, trebuie legata la activitatea pentru selectarea unui test pe care nu am facut-o inca
