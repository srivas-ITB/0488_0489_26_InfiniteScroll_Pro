# App 26 InfiniteScroll_Pro

Gestió avançada per fer un scroll infinit:

En base a l'App 25 InfiniteScroll_Basic s'han fet els següents canvis:

- Fem servir Paging 3  (veure build.gradle.kts (Module)
- El viewModel ja no implementa una funció "loadNextPage()" sino que conté un objecte Pager al que s'assigna una font de dades MyPagingSource
- La font de dades retorna un conjunt de la nova entitat MyData
- La font de dades simula la càrrega de nous elements, esperant un temps per simular la latència d'accés a una API i afegint nous elements a la llista de resultats

És una simulació bàsica i en cap moment s'accedeix a l'API.

La càrrega de dades és dinàmica, i també descarrega les dades que ja no presenta per estalviar memòria.


> [!WARNING]
> MOLT IMPORTANT!!  
> - Cal identificar de manera única els elements de la LazyColumn.
> - Si no es fa, s'identifiquen amb la seva posició dins la llista d'elements. 
> - Però en fer la paginació i eliminar els elements anteriors, l'element 100 passa a ser el 80 per haver-se eliminat la pàgina anterior.
> - Però tornem a tenir 100 elements perquè hem carregat una nova pàgina.
> - El LazyColumn es pensa encara que està visualitzant l'element 100, que ara és el darrer element de la nova pàgina.
> - Això fa disparar la càrrega d'una nova pàgina i entrem en un bucle infinit.
>
> 