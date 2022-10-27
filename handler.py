import sys
sys.path.insert(0, 'vendor')

import os
import requests
import random
import json

PREFIX = '?'
OPTIONS = [
    'It is certain.', 'It is decidedly so.', 'Without a doubt.', 'Yes - definitely.', 'You may rely on it.',
    'As I see it, yes.', 'Most likely.', 'Outlook good.', 'Yes.', 'Signs point to yes.',
    'Reply hazy, try again.', 'Ask again later.', 'Better not tell you now.', 'Cannot predict now.', 'Concentrate and ask again.',
    'Don\'t count on it.', 'My reply is no.', 'My sources say no.', 'Outlook not so good.', 'Very doubtful.'
]


def receive(event, context):
    message = json.loads(event['body'])

    bot_id = message['bot_id']
    response = process(message)
    if response:
        send(response, bot_id)

    return {
        'statusCode': 200,
        'body': 'ok'
    }


def process(message):
    # Prevent self-reply
    if message['sender_type'] != 'bot':
        if message['text'].startswith(PREFIX):
            return random.choice(OPTIONS)


def send(text, bot_id):
    url = 'https://api.groupme.com/v3/bots/post'

    message = {
        'bot_id': bot_id,
        'text': text,
    }
    r = requests.post(url, json=message)
