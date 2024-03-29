This is the README file of the Automation Project. The project contains 5 automated test cases. The steps for each one of them are described below.

#1 Test Login

1. Open the Skillo website http://training.skillo-bg.com:4200/
2. Validate we are on the correct page
3. Click the "Login" button
4. Validate the "Login" page is loaded
5. Validate the "Sign In" form is visible
6. Input valid username
7. Input valid password
8. Click the "Sign in" button
9. Click the "Profile" button
10. Validate the "Profile" page is loaded
11. Validate that the username matches the username provided



#2 Test Registration

1. Open the Skillo website http://training.skillo-bg.com:4200/
2. Validate we are on the correct page
3. Click "Login"
4. Validate the "Login" page is loaded
5. Validate the "Sign In" form is visible
4. Click the "Register" button
5. Validate the URL of the Registration form
6. Validate the "Sign Up" form is visible
7. Input valid username
8. Input valid email
9. Input valid password
11. Input confirm password
13. Click the "Sign In" button
14. Wait for the Home page to load
15. Click the "Profile" button
16. Validate the user profile link is loaded
17. Validate the username provided matches the username on the Profile page



#3 Test Post Interactions

1. Perform all the steps from the Test Login
2. Click the "Home" button
3. Click on the profile of the first user
4. Click the "Follow" button
5. Click on "All" posts
6. Click the first image
7. Click the "Like" button
8. Click the "Dislike" button
9. Write a comment and wait until it is visible


#4 Test Creating a New Private Post

1. Perform all the steps from the Test Login
2. After the final step click on the "New post" button
3. Validate the "New post" page is loaded
4. Upload a picture
5. Validate that the picture has been uploaded
6. Validate that the picture is the correct one
7. Fill in the caption
8. Toggle to a Private Post
9. Click on the "Create Post" button
10. Click on "All" posts


#5 Test Logout

1. Perform all the steps from the Test Login
2. Get the number of posts and print it
3. Get the number of followers and print it
4. Get the number of followings and print it
5. Click the "Logout" button
6. Validate that the user has been logged out