$('#searchButton').click(function(e){
    e.preventDefault();
    let materialName = $('#materialName').val();
    let materialUnit = $('#radio1').val();
    searchResult.empty();
    $.ajax({
        type: 'GET',
        url: '/searchBooks',
        data: {searchField: searchField },
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Content-Type', 'application/json')},
        success: function (response) {
            searchResult.empty();
            response.forEach(function (BookDto) {
                searchResult.append('<tr><td>' + BookDto.id + '</tr><td>' + BookDto.title + '</td><td>' + BookDto.isbn + '</td><td>' +
                    BookDto.author +'</td><td><a href="/add_to_wishlist/'+BookDto.id + '">' +
                    ('Add to wishlist') + '</a> ' +'</td></tr>');
            })
        },
        error: function () {
            console.log("Error in searching of the book!");
        }
    });
});