var app = angular.module('sample', []);

app.controller("SampleController", function ($scope, $http) {


    $scope.getList = function () {
        $http.get('/rest/sample').success(function (data, status, headers, config) {
            $scope.bookList = data;
        }).error(function (data, status, headers, config) {
            if (data.message === 'Time is out') {
                $scope.finishByTimeout();
            }
        });
    };

    $scope.delete = function (name) {

            if (name === "") name = undefined;

            $http.delete('/rest/sample/delete/' + name).success(function (data, status, headers, config) {
                $scope.isDeleteError = false;
                for (var i = 0; i < $scope.bookList.length; i++) {
                    var j = $scope.bookList[i];
                    if (angular.lowercase(j.id.book.title) === angular.lowercase(name)) {
                        $scope.bookList.splice(i, 1);
                        break;
                    }
                }
            }).error(function (data, status) {
                $scope.errorMessage = data.message;
                $scope.isDeleteError = true;
                $scope.isAddError = false;
                console.warn("Status code: " + status + "\nError: " + data.message);
            });
        };

    $scope.addBook = function (name, author, publisher) {

        if (name === "") name = undefined;
        if (author === "") author = undefined;
        if (publisher === "") publisher = undefined;

            $http.post('/rest/sample/add/' + name + "/" + author + "/" + publisher).success(function (data, status, headers, config) {
                $scope.isError = false;
                $scope.bookList.splice(0, 0, data);
            }
            ).error(function (data, status) {
                $scope.errorMessage = data.message;
                $scope.isAddError = true;
                $scope.isDeleteError = false;
                console.warn("Status code: " + status + "\nError: " + data.message);
            });

        };

});
