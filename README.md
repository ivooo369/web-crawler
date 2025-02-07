# Web Crawler for Job Listings

## Overview
This project implements a web crawler that scrapes job listings from two websites concurrently. The crawler extracts the job title and URL from each job posting. The crawler works entirely in the backend and does not require a web interface. The project uses Java and Jsoup for HTML parsing.

## Features
- **Concurrent Crawling**: The crawler is configurable to scrape a specified number of job listings concurrently, allowing flexibility in how many listings are scraped at once.
- **Configurable Websites**: The crawler is configured to scrape job listings from two websites.
- **In-memory Storage**: The crawled job listings are stored in memory.
- **Design Patterns**: The project uses 4 design patterns: Singleton, Factory, Observer and Strategy.
- **Unit Testing**: Most of the code is covered with unit tests.
- **SOLID Principles**: The code follows SOLID principles for maintainability and scalability.

## Technologies Used
- **Java**
- **Jsoup** (for web scraping)
- **JUnit** (for unit testing)
- **Mockito** (for mocking in tests)

## Configuration
The crawler can be configured to scrape job listings from specific websites and determine how many listings should be scraped concurrently.

### Supported Websites
- `https://www.itjobboard.co.uk/jobs/`
- `https://www.itjobsworldwide.com/jobs/backend-development`

The crawler configuration is flexible, allowing the user to choose a subset of these websites.

## How It Works
1. **Web Crawler**: The web crawler uses Jsoup to parse HTML pages and extract job titles and URLs.
2. **Concurrency**: The number of concurrent crawls can be configured based on the user's requirements.
3. **In-memory Storage**: All scraped job listings are stored in memory, ensuring fast retrieval and processing.

## Design Patterns
The project incorporates the following design patterns:
- **Singleton Pattern**: To provide a single instance of the configuration class (`CrawlerConfig`) across the application, ensuring consistent configuration settings.
- **Strategy Pattern**: For selecting different crawling strategies based on the website.
- **Observer Pattern**: To log messages or notify other components about the progress or issues.
- **Factory Pattern**: To create instances of web crawlers based on the target websites.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ivooo369/web-crawler.git
