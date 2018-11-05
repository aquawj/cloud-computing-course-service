# course-service-REST-API
## github：
https://github.com/aquawj/course-service-REST-API
## aws：
http://courseservice-env.f7qscjk2ii.us-west-2.elasticbeanstalk.com/
## API:

#### Student

1. GET

     webapi/students
     
     webapi/students/{studentId}
     
     webapi/students/byCourse/?courseId=cyse6150
     
2. POST 

      webapi/students/
      
      body: 
      
      
      {
              "enrolledCourses": [
                  "cloud",
                  "data",
                  "java"
              ],
              "id": 1,
              "image": "www.image.com/22",
              "name": "Jane",
              "programName": "IS"
          },
          
          {
              "enrolledCourses": [
                  "cloud",
                  "web",
                  "PDP"
              ],
              "id": 2,
              "image": "www.image.com/2",
              "name": "Ellen",
              "programName": "CS"
          },
          
          {
              "enrolledCourses": [
                  "java",
                  "web",
                  "data"
              ],
              "id": 3,
              "image": "www.image.com/62",
              "name": "David",
              "programName": "IS"
          },
          
          {
              "enrolledCourses": [
                  "web",
                  "java"
              ],
              "id": 4,
              "image": "www.image.com/4",
              "name": "Ethan",
              "programName": "IS"
          }
          
3.PUT

   webapi/students/{studentId}
       
   body:
        
       {
        
        "id": 1,
        "image": "www.image.com/10",
        "name": "Jane"
                        
        }

4.DELETE 

webapi/students/{studentId}
     
     
#### Courses

1. GET

     webapi/courses/
     
     webapi/courses/{courseid}
     
     e.g. webapi/courses/cyse6250
     
     webapi/courses/cyse6150

2. POST 

      webapi/courses/
      
      body: 
      
      
        {
              "id": "cyse6150",
              "lectures": [
                  1,
                  2
              ],
              "name": "cloud",
              "students": [
                  1,
                  3
              ]
          },
          {
              "id": "cyse6250",
              "lectures": [
                  3
              ],
              "name": "data",
              "students": [
                  2
              ]
          },
          {
              "id": "cs61",
              "lectures": [
                  1
              ],
              "name": "PDP",
              "students": [
                  4
              ]
          },
          {
              "id": "info5100",
              "lectures": [],
              "name": "java",
              "students": [
                  1,
                  2,
                  3
              ],
              "taId": 2
          },
          {
              "id": "cs300",
              "lectures": [],
              "name": "PDP",
              "students": [
                  4
              ]
          }
      
3.PUT

   webapi/courses/{courseid}
   
   e.g. webapi/courses/cyse6150
       
   body: 
   
       {
         "id": "cs300",
         "name": "PDP",
         "students": [
                        2,4
                     ]
        }
        
4.DELETE 

   webapi/courses/{courseid}
   
   
#### Program
   
   1. GET
   
        webapi/programs
        
        webapi/programs/{programName}
        

        
   2. POST 
   
         webapi/programs
         
         body: 
         
         
           {
                 "courses": [
                     "PDP",
                     "web",
                     "mobile"
                 ],
                 "name": "CS"
             },
             {
                 "courses": [
                     "java",
                     "web",
                     "data",
                     "cloud"
                 ],
                 "name": "IS"
             }
   3.PUT

   webapi/programs/{programName}
   
   e.g. webapi/programs/IS
       
   body: 
   
       {
        "courses": [
                        "java",
                        "web",
                        "cloud"
                        ],
        "name": "IS"
        }
        
   4.DELETE 

   webapi/programs/{programName}
   
   webapi/programs/{programName}/{courseId}
   
   
   e.g. webapi/programs/CS
   
   webapi/programs/CS/PDP
   
   
   #### Lecture
      
  1. GET
      
        webapi/lectures
           
        webapi/lectures/{lectureId}
           
   
           
  2. POST 
      
        webapi/lectures
            
        body: 

    {
            "id": 1,
            "materials": [
                "material1",
                "head1"
            ],
            "notes": "notes1"
        },
        {
            "id": 2,
            "materials": [
                "material2",
                "head2"
            ],
            "notes": "notes2"
        }
                
  3.PUT
   
  webapi/lectures/{lectureId}
      
          
  body: 
      
          {
              "id": 2,
              "materials": [
                              "material2",
                              "head2"
                              ],
              "notes": "notes3"
          }
           
   4.DELETE 
   
   webapi/lectures/{lectureId}
   
   
