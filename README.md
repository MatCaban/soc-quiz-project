# QUIZ Project
## This project, developed as part of the StreetOfCode course, implements a simple quiz application. The quiz dynamically loads questions from txt files and stores player scores (current session and total) in a separate JSON file.

### How it Works

### Welcome and Player Selection:
  * Upon starting, the quiz greets the player.
  * It checks for existing player data (stored as JSON files locally).
  * If no existing players are found, the player is prompted to create a new profile by choosing a name.
  * If existing player data is found, the quiz lists the available player profiles (which are also the file names) and asks if the player wants to continue with an existing profile or create a new one.

### Topic Selection:
  * The player is then asked to select the categories from which they wish to answer questions.
  * Questions are stored in txt files, with each file representing a different topic (e.g., "science", "geography", ...).

### Quiz Gameplay:
  * The player is presented with four questions.
  * Each question has four answer options.
  * For each question, it's explicitly stated whether there is only one correct answer or multiple correct answers.

### Answer Validation: Player responses are validated to ensure they are:
  * Numbers between 1 and 4.
  * Unique (e.g., "113" is invalid, "123" is valid).
  * Players are notified if they enter an invalid answer.

### Scoring:
  * If a player provides multiple answers for a question with only one correct answer, they lose a point.
  * If a player provides only one answer, or doesn't provide all correct answers, for a question with multiple correct answers, they lose a point.

### End of Game:
  * At the end of the game, the player is prompted whether they want to repeat the quiz with a new set of questions or exit the game.

#### Extending the Quiz
  * Adding New Topics: To add more question categories, simply create and save new txt files with the desired questions.
