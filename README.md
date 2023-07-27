# SmartRecall: Space Repetition and Summarizer Software

SmartRecall is a powerful and efficient software tool designed to enhance your learning experience by implementing space repetition and summarization techniques. This tool is perfect for students, researchers, and knowledge seekers who want to optimize their study habits and retain information effectively.

## Features

### Summarize .md Files
SmartRecall navigates through .md (Markdown) files located at the given path, intelligently scanning for important points enclosed within double square brackets "[[]]". It then organizes these important points into a comprehensive study guide, structured under relevant headings.

### Automatic Question Bank Generation
Not only does SmartRecall summarize the crucial content, but it also extracts questions and answers from the important points identified in the .md files. These questions and answers are used to create a question bank file. The question bank is the backbone of the space repetition revision process, enabling you to review and reinforce your knowledge.

### Space Repetition Revision
SmartRecall facilitates efficient space repetition learning, allowing you to review the question bank based on your last accessed date. The software intelligently prioritizes questions that you marked as hard during the previous revisions, ensuring you focus on challenging topics first. You can set the number of questions you want to review during each session.

### User-Friendly Command Line Interface
SmartRecall's command line interface makes it incredibly easy to use. To create a study guide and question bank, simply provide the path to the folder containing your .md notes file, choose your preferred ordering (created, modified, or alphabetical), and specify the output location for the study guide. With its intuitive interface, SmartRecall streamlines the summarization and revision process.

### Thoroughly Tested with JUnit5
We take software quality seriously. SmartRecall has undergone rigorous testing using JUnit5 to ensure stability and reliability. You can rely on the software to perform as expected and provide consistent results.

## Getting Started

1. Clone this repository to your local machine.
2. Locate the `Driver.java` file within the `SmartRecall/src/main/java/cs3500/pa01` directory.
3. To create a study guide and question bank, execute the following command:

java Driver [path-to-folder] [ordering-flag] [path-to-output-studyguide]


- `<path-to-folder>`: The path to the folder containing the .md files with important points.
- `<ordering-flag>`: Choose from "created", "modified", or "filename" to order the study guide accordingly.
- `<path-to-output-studyguide>`: The path to the output location, including the name of the study guide.

4. If no arguments are passed, you can start a space repetition session using SmartRecall.

## Contribute and Support

SmartRecall is an open-source project, and we welcome contributions from the community. If you encounter any issues, have suggestions for improvements, or want to be part of this exciting project, feel free to submit pull requests or raise issues on our GitHub repository.

Remember, effective learning is key to success, and SmartRecall is here to help you achieve your academic and intellectual goals. Happy learning with SmartRecall!

*Note: The above information is subject to change as the project evolves. Please refer to the latest documentation and the GitHub repository for the most up-to-date information.*
