# App 25 InfiniteScroll_Basic

Gestió bàsica per fer un scroll infinit:
    - La Pantalla1 conté un component InfiniteScrollScreen que realment és un LazyColum amb la gestió de la càrrega dels següents elements
    - El viewModel simula la càrrega de nous elements, esperant un temps per simular la latència d'accés a una API i afegint nous elements a la llista de resultats

És una simulació molt bàsica i en cap moment s'accedeix a l'API.

La càrrega de dades és dinàmica, però no fa cap descàrrega de dades. 
Amb dades infinites es quedaria sense memòria RAM. 
No hi ha problemes de repintat de pantalla perquè la LazyColumn ja ho gestiona.