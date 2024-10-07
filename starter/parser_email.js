import fs from 'fs';
import { MailParser } from 'mailparser';


function parseEmail(filePath) {
    let emailFile = fs.readFileSync(filePath, 'utf8');

    let parser = new MailParser();

    parser.on('headers', headers => {
        console.log('From: ', headers.get('from').text);
        console.log('To: ', headers.get('to').text);
        console.log('Subject: ', headers.get('subject'));
    });

    parser.on('data', data => {
        if (data.type === 'text') {
            console.log(data.text);
        }
    });

    parser.write(emailFile);
    parser.end();
}

parseEmail('email.eml');