# quizapptw

15.12.2018
George

* Am facut o structura a proiectului React dupa modelul https://daveceddia.com/react-project-structure/ . Mi s-a parut cel mai adecvat pentru ce avem noi de facut
* Dupa un research de vreo 3 ore, parerea mea este ca cel mai bun framework de UI pentru React este SemanticUI (https://react.semantic-ui.com/). Alternativele ar fi __Bulma__, __MaterialUI__, __React Bootstrap__(documentatie naspa si slightly outdated) si __Ant__ (care mi s-a parut foarte interesant dar ar fi cam heavy). Mi se pare ca dintre toate Semantic are cea mai buna documentatie, este popular si foarte foarte usor de folosit (trebuie doar sa importi elementele folosite si apoi esti good to go). Mai este folosit si de catre Netflix si Amazon

**! Nu se poate folosi Bootstrap pt ca este dependent de jQuery si React nu este compatibil cu jQuery.**
>jQuery is a DOM manipulation library. It reads from and writes to the DOM. React uses a virtual DOM (a JavaScript representation of the >real DOM). React only writes patch updates to the DOM, but never reads from it.
>It is not feasible to keep real DOM manipulations in sync with React's virtual DOM. Because of this, all jQuery functionality has been re-implemented in React.

Good to read about UI frameworks:
https://www.reddit.com/r/javascript/comments/7qrcas/what_is_you_favorite_ui_framework_for_react/
https://www.reddit.com/r/reactjs/comments/9fhxj0/what_are_some_of_the_ui_libraries_you_used_for/
https://hackernoon.com/23-best-react-ui-component-libraries-and-frameworks-250a81b2ac42

Inca o chestie importanta aici, pentru a putea folosi CSS cu Semantic UI, la majoritatea elementelor trebuie folosit !important pentru a face override la setarile implicite ale frameworkului.

`label{
    color:red !important;
}
`

Si in loc de `class` se foloseste `className`

Am facut un login super basic pentru a testa. Voi lucra la pagina de login soon si apoi ar trebui sa ne apucam sa facem componenta de meniu, user profile & so on. Ar trebui sa facem componentele skinny (fara functionalitate) deocamdata, iar apoi componentele fatty (logica). 
>First, we’d separate the UI layer into a presentational component. Then, we’d wrap that presentational component in a larger container component which passes down props into presentational components as children. In this way, the container can then handle the state and any other app logic.

Am mai citit chestii despre Redux si cum ca ar fi folositor pentru routing (majoritatea proiectelor React folosesc si Redux). Mai trebuie sa ne documentam despre asta.

07.01.2019 
George 

* Am implementat o parte din user storyul pentru Student cu valori hardcodate (lipseste partea de test efectiv) 
* Am facut navbar (va fi similar si la teacher) si routing cu react-router 
* Am facut pagina de login care va duce la teacher sau la student in functie de alegerea din dropdown 
* Stylingul il lasam mai pe la final ca sa nu pierdem timpul si sa avem un MVP

LE: Am terminat routingul pt toata aplicatia, am facut navbarul pt teacher, am facut partea de UI fara prea mult styling (e decent) la 
teacher: profile + create test.

08.01.2019
George

* Am terminat UI (cu exceptia quizzului in sine care va fi format din modale succesive) + styling refinements la final
* Probabil ca voi face niste date mockuite pe https://getsandbox.com/ pana reusim sa facem backendul
* Left to do: Backend + DB

09.01.2019
George

Structura DB v1:
Teacher:

* ProfileStatistics: 
- Tests (integer)
- QuestionCategories (integer)
- Questions (integer)
- GivenAssignments (integer)

* Tests:
- ID
- TestName (varchar)
- Questions (with answers mapped) - one to many (one test, many questions)
- Shuffle (true/false)
- Feedback (true/false)
- Result (true/false)
- OneWay (true/false)
- Time (integer)
- Retrieves (integer)
- Active (true/false)

* Categories:
- ID
- Category (varchar) 
- Questions (one category, many questions)

* Questions:
- ID
- Question (varchar)
- Category (varchar) (one question, one category)
- Answers (? maybe array)

* Results:
- ID
- Name (varchar)
- Group (integer)
- Score (integer)

* AssignmentsHistory:
- ID
- Date (varchar or date)
- Result (one result, one assignment)
- TestName (varchar) (one assignment, one test)

Student:

* AssignmentsHistory:
- ID
- Date (date)
- Test (varchar) (one assignment, one test)
- Score (integer)


Structura DB v2:

* User:
    - ID
    - Name (varchar)
    - Username (varchar)
    - HashedPassword (varchar)
    - Group (integer)
    - isStudent (bool)

* Tests:
    - ID
    - TestName (varchar)
    - Questions (with answers mapped) - one to many (one test, many questions)
    - Shuffle (bool)
    - Feedback (bool)
    - Result (bool)
    - OneWay (bool)
    - Time (integer)
    - Retrieves (integer)
    - isActive (bool)

* QuestionCategories:
    - ID
    - Category (varchar) 
    - Questions [or Question_ID] (one category, many questions)

* Questions:
    - ID
    - Question (varchar)
    - QuestionCategory_ID (one question, one category)

* Answers:
    - ID
    - AnswerText (varchar)
    - isCorrect (bool)
    - Question_ID

* FinishedTests
    - ID
    - Test_ID
    - Username
    - Date (date)
    - Score (integer)