var app = angular.module('search', []);

app.controller("SearchController", function ($scope, $http) {
    $scope.search = function (name) {
        if (name === "") name = undefined;
            $http.post('/rest/search/' + name).success(function (data, status, headers, config) {
                $scope.isSearchError = false;
                $scope.bookList = data;
            }
            ).error(function (data, status) {
                $scope.errorMessage = data.message;
                $scope.isSearchError = true;
                console.warn("Status code: " + status + "\nError: " + data.message);
            });
    };
});
