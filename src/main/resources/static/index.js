(function () {
    angular.module('app', []);

})();

angular.module('app').controller('indexController', function ($scope, $http) {

    let address = 'http://localhost:8080';
    $scope.newStudent = {};

    $scope.pageNumber = 0;

    $scope.filteredStudents = function() {
        $http({
            url: address + '/students',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                mark_lesser_than: $scope.filter ? $scope.filter.mark_lesser_than : null,
                mark_greater_than: $scope.filter ? $scope.filter.mark_greater_than : null,
                page_num: $scope.pageNumber
            }
        })
        .then(function(response) {
            $scope.firstPage = response.data.first;
            $scope.lastPage = response.data.last;
            $scope.StudentList = response.data.content;
            if ($scope.StudentList.toString() === '') {
                if ($scope.pageNumber > 0) {
                    $scope.prevPage();
                }
            }
        })
    }

    $scope.filteredStudents();

    $scope.prevPage = function() {
        $scope.pageNumber--;
        $scope.filteredStudents();
    }

    $scope.nextPage = function() {
        $scope.pageNumber++;
        $scope.filteredStudents()
    }

    $scope.clearFilter = function() {
        $scope.filter.name_part = null;
        $scope.filter.mark_lesser_than = null;
        $scope.filter.mark_greater_than = null;
    }

    $scope.addUpdateStudent = function() {
        if ($scope.newStudent.id == null) {
            $scope.addStudent();
        } else {
            $scope.updateStudent();
        }
    }

    $scope.updateStudent = function() {
        $http({
            url: address + '/students',
            method: 'PUT',
            data: $scope.newStudent
        })
            .then(function() {
                $scope.newStudent.id = null;
                $scope.newStudent.name = null;
                $scope.newStudent.mark = null;
                $scope.filteredStudents();
            })
    }

    $scope.addStudent = function() {
        $http({
            url: address + '/students',
            method: 'POST',
            data: $scope.newStudent
        })
            .then(function() {
                $scope.newStudent.name = null;
                $scope.newStudent.mark = null;
                $scope.filteredStudents();
            })
    }

    $scope.deleteStudent = function(studentId) {
        $http({
            url: address + '/students/' + studentId,
            method: 'DELETE'
        }).then(function(){
            $scope.filteredStudents();
        })
    }

    $scope.editStudent = function(student) {
        console.log(student);
        $scope.newStudent.id = student.id;
        $scope.newStudent.name = student.name;
        $scope.newStudent.mark = student.mark;
    }

});
